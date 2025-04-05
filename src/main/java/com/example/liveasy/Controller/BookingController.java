package com.example.liveasy.Controller;

import com.example.liveasy.DTO.BookingDTO;
import com.example.liveasy.Interface.BookingService;
import com.example.liveasy.Model.Booking;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        logger.info("POST /booking request received");
        Booking newBooking = bookingService.createBooking(bookingDTO);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getBookings(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) String transporterId) {
        logger.info("GET /booking request received with shipperId: {}, transporterId: {}", shipperId, transporterId);
        List<Booking> bookings = bookingService.getBookings(shipperId, transporterId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable UUID bookingId) {
        logger.info("GET /booking/{} request received", bookingId);
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable UUID bookingId,
            @Valid @RequestBody BookingDTO updateBookingDTO) {
        logger.info("PUT /booking/{} request received", bookingId);
        Booking booking = bookingService.updateBooking(bookingId, updateBookingDTO);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable UUID bookingId) {
        logger.info("DELETE /booking/{} request received", bookingId);
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}