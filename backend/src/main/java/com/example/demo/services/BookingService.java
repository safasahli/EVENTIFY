package com.example.demo.services;

import com.example.demo.entities.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> getAllBookings();
    Optional<Booking> getBookingById(Long id);
    Booking saveBooking(Booking booking);
    void deleteBooking(Long id);
    List<Booking> findBookingsByCustomerName(String customerName);
}
