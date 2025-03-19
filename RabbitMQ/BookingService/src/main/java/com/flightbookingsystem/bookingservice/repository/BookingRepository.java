package com.flightbookingsystem.bookingservice.repository;

import com.flightbookingsystem.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findById(Long id);
    List<Booking> findAllByFlightNumber(String flightNumber);
}
