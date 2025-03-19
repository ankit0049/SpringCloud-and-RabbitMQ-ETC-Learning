package com.flightbookingsystem.fareservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingEvent {
    private String flightNumber;
    private String passengerName;
}
