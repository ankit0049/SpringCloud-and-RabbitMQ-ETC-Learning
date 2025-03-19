package com.flightbookingsystem.bookingservice.service;

import com.flightbookingsystem.bookingservice.entity.Booking;
import com.flightbookingsystem.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> getBooking(Long id) {
        return bookingRepository.findById(id);
    }

    // ✅ Find all bookings by Flight Number
    public List<Booking> getBookingsByFlightNumber(String flightNumber) {
        return bookingRepository.findAllByFlightNumber(flightNumber);
    }
}
