package com.flightbookingsystem.fareservice.service;

import com.flightbookingsystem.fareservice.dto.BookingEvent;
import com.flightbookingsystem.fareservice.entity.Fare;
import com.flightbookingsystem.fareservice.repository.FareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class BookingEventListener {
    private final FareRepository fareRepository;

    @RabbitListener(queues = "bookingQueue")
    public void handleBookingEvent(BookingEvent bookingEvent) {
        System.out.println("Received booking for fare processing: " + bookingEvent);

        Optional<Fare> fare = fareRepository.findByFlightNumber(bookingEvent.getFlightNumber());

        fare.ifPresentOrElse(
                f -> System.out.println("Fare found: " + f.getPrice()),
                () -> {
                    System.out.println("Fare not found, creating new fare entry...");
                    Fare newFare = new Fare();
                    newFare.setFlightNumber(bookingEvent.getFlightNumber());
                    newFare.setPrice(Math.random()*1000+1000); // Set a default price

                    Fare savedFare = fareRepository.save(newFare);
                    System.out.println("New fare saved: " + savedFare);
                }
        );
    }
}
