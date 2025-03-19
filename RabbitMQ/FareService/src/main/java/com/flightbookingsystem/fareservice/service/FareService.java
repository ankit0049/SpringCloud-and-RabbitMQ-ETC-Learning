package com.flightbookingsystem.fareservice.service;

import com.flightbookingsystem.fareservice.entity.Fare;
import com.flightbookingsystem.fareservice.repository.FareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FareService {
    private final FareRepository fareRepository;

    public Optional<Fare> getFareByFlightNumber(String flightNumber) {
        return fareRepository.findByFlightNumber(flightNumber);
    }
}
