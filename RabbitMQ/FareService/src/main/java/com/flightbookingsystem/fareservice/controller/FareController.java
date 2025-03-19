package com.flightbookingsystem.fareservice.controller;

import com.flightbookingsystem.fareservice.entity.Fare;
import com.flightbookingsystem.fareservice.repository.FareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/fares")
@RequiredArgsConstructor
public class FareController {
    private final FareRepository fareRepository;
    private final RestTemplate restTemplate;

    // ✅ Get Fare and Booking Details by flightNumber
    @GetMapping("/{flightNumber}")
    public ResponseEntity<?> getFareAndBookingDetails(@PathVariable String flightNumber) {
        System.out.println("\u001B[31mFlightNumber is " + flightNumber + " Yahi hai\u001B[0m");

        // 🔍 Fetch Fare details
        Optional<Fare> fare = fareRepository.findByFlightNumber(flightNumber);

        // Call BookingService with correct URL
        String bookingServiceUrl = "http://localhost:8083/bookings/flight/" + flightNumber;
        ResponseEntity<Object> bookingResponse = restTemplate.getForEntity(bookingServiceUrl, Object.class);


        // 📝 Prepare Response
        Map<String, Object> response = new HashMap<>();
        response.put("fare", fare.orElse(null));

        if (bookingResponse.getStatusCode().is2xxSuccessful()) {
            response.put("booking", bookingResponse.getBody());
        } else {
            response.put("booking", "No bookings found for flight: " + flightNumber);
        }

        return ResponseEntity.ok(response);
    }

    // ✅ Add a new Fare manually (for testing)
    @PostMapping
    public ResponseEntity<Fare> addFare(@RequestBody Fare fare) {
        Fare savedFare = fareRepository.save(fare);
        return ResponseEntity.ok(savedFare);
    }
}
