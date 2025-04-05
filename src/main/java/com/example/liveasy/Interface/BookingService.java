package com.example.liveasy.Interface;

import com.example.liveasy.DTO.BookingDTO;
import com.example.liveasy.Model.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    Booking createBooking(BookingDTO booking);
    List<Booking> getBookings(String shipperId, String transporterId);
    Booking getBookingById(UUID bookingId);
    Booking updateBooking(UUID bookingId, BookingDTO updateBooking);
    void deleteBooking(UUID bookingId);
}