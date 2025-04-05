package com.airline.Airline.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.Airline.models.Booking;
import com.airline.Airline.models.User;
import com.airline.Airline.services.BookingService;
import com.airline.Airline.services.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

     @Autowired
    private BookingService bookingService;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/all-bookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

}
