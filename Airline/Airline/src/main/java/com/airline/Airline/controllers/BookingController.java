package com.airline.Airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.airline.Airline.dto.BookingRequest;
import com.airline.Airline.services.BookingService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")

public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public String bookFlight(@RequestBody BookingRequest request) {
        return bookingService.bookFlight(request.getUserDTO(), request.getFlightNumber(), request.getNoOfSeats());
    }
}
