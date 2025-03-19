package com.flightbookingsystem.fareservice.repository;

import com.flightbookingsystem.fareservice.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FareRepository extends JpaRepository<Fare, Long> {


    Optional<Fare> findById(Long id);

    Optional<Fare> findByFlightNumber(String flightNumber);
}
