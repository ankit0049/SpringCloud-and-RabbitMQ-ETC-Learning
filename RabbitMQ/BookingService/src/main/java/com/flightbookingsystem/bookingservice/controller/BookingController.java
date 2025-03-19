package com.flightbookingsystem.bookingservice.controller;

import com.flightbookingsystem.bookingservice.entity.Booking;
import com.flightbookingsystem.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    // ✅ Create a new Booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    // ✅ Fetch Booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        return bookingService.getBooking(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Fetch Bookings by Flight Number
    @GetMapping("/flight/{flightNumber}")
    public ResponseEntity<?> getBookingsByFlightNumber(@PathVariable String flightNumber) {
        List<Booking> bookings = bookingService.getBookingsByFlightNumber(flightNumber);

        if (bookings.isEmpty()) {
            return ResponseEntity.status(404).body("No bookings found for flight: " + flightNumber);
        }

        return ResponseEntity.ok(bookings);
    }
}
