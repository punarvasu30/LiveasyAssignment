package com.example.liveasy.Service;

import com.example.liveasy.DTO.BookingDTO;
import com.example.liveasy.Exception.InvalidRequestException;
import com.example.liveasy.Exception.ResourceNotFoundException;
import com.example.liveasy.Interface.BookingService;
import com.example.liveasy.Model.Booking;
import com.example.liveasy.Model.BookingStatus;
import com.example.liveasy.Model.Load;
import com.example.liveasy.Model.LoadStatus;
import com.example.liveasy.Repository.BookingRepository;
import com.example.liveasy.Repository.LoadRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingRepository;
    private final LoadRepository loadRepository;

    @Override
    @Transactional
    public Booking createBooking(BookingDTO bookingDTO) {
        logger.info("Creating booking for load: {}", bookingDTO.getLoadId());

        Load load = loadRepository.findById(bookingDTO.getLoadId())
                .orElseThrow(() -> new ResourceNotFoundException("Load", "id", bookingDTO.getLoadId()));

        if(load.getStatus() == LoadStatus.CANCELLED) {
            logger.warn("Cannot book a CANCELLED Load: {}", load.getId());
            throw new InvalidRequestException("Cannot book a CANCELLED Load");
        }

        if(load.getStatus() == LoadStatus.BOOKED) {
            logger.warn("Load is already BOOKED: {}", load.getId());
            throw new InvalidRequestException("Load is already BOOKED");
        }

        Booking booking = Booking.builder()
                .loadId(bookingDTO.getLoadId())
                .transporterId(bookingDTO.getTransporterId())
                .proposedRate(bookingDTO.getProposedRate())
                .comment(bookingDTO.getComment())
                .status(BookingStatus.PENDING)
                .requestedAt(Timestamp.from(Instant.now()))
                .build();

        Booking savedBooking = bookingRepository.save(booking);
        logger.info("Booking created successfully with ID: {}", savedBooking.getId());

        load.setStatus(LoadStatus.BOOKED);
        loadRepository.save(load);
        logger.info("Load status updated to BOOKED: {}", load.getId());

        return savedBooking;
    }

    @Override
    public List<Booking> getBookings(String shipperId, String transporterId) {
        logger.info("Fetching bookings with filters - shipperId: {}, transporterId: {}", shipperId, transporterId);

        if (shipperId != null && transporterId != null) {
            return bookingRepository.findByShipperIdAndTransporterId(shipperId, transporterId);
        } else if (shipperId != null) {
            return bookingRepository.findByShipperId(shipperId);
        } else if (transporterId != null) {
            return bookingRepository.findByTransporterId(transporterId);
        }

        logger.info("Fetching all bookings");
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(UUID bookingId) {
        logger.info("Fetching booking by ID: {}", bookingId);
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", bookingId));
    }

    @Override
    @Transactional
    public Booking updateBooking(UUID bookingId, BookingDTO updateBookingDTO) {
        logger.info("Updating booking with ID: {}", bookingId);

        Booking existingBooking = getBookingById(bookingId);

        // Only update status when moving to ACCEPTED
        if (updateBookingDTO.getComment() != null) {
            existingBooking.setComment(updateBookingDTO.getComment());
        }

        if (updateBookingDTO.getProposedRate() != null) {
            existingBooking.setProposedRate(updateBookingDTO.getProposedRate());
        }

        // If status is being updated to ACCEPTED
        if (BookingStatus.ACCEPTED.name().equals(updateBookingDTO.getStatus().name())) {
            existingBooking.setStatus(BookingStatus.ACCEPTED);

            Load load = loadRepository.findById(existingBooking.getLoadId())
                    .orElseThrow(() -> new ResourceNotFoundException("Load", "id", existingBooking.getLoadId()));

            // Ensure load is BOOKED when booking is ACCEPTED
            load.setStatus(LoadStatus.BOOKED);
            loadRepository.save(load);
            logger.info("Load status confirmed as BOOKED: {}", load.getId());
        } else if (BookingStatus.REJECTED.name().equals(updateBookingDTO.getStatus().name())) {
            existingBooking.setStatus(BookingStatus.REJECTED);
        }

        Booking updatedBooking = bookingRepository.save(existingBooking);
        logger.info("Booking updated successfully: {}", updatedBooking.getId());
        return updatedBooking;
    }

    @Override
    @Transactional
    public void deleteBooking(UUID bookingId) {
        logger.info("Deleting booking with ID: {}", bookingId);

        Booking booking = getBookingById(bookingId);
        UUID loadId = booking.getLoadId();

        bookingRepository.delete(booking);
        logger.info("Booking deleted successfully: {}", bookingId);

        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load", "id", loadId));

        load.setStatus(LoadStatus.CANCELLED);
        loadRepository.save(load);
        logger.info("Load status updated to CANCELLED: {}", loadId);
    }
}
