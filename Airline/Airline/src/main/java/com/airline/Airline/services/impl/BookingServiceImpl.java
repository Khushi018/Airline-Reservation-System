package com.airline.Airline.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.Airline.dto.UserDTO;
import com.airline.Airline.models.Booking;
import com.airline.Airline.models.Flight;
import com.airline.Airline.repository.BookRepo;
import com.airline.Airline.repository.FlightRepo;

import com.airline.Airline.enums.BookingStatus;
import com.airline.Airline.services.BookingService;


@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookRepo bookingRepository;

    @Autowired
    private FlightRepo flightRepository;

    @Override

    public String bookFlight(UserDTO userDTO, Long flightId, int noOfSeats) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null) {
            return "Flight not found!";
        }

        if (flight.getAvailableSeats() < noOfSeats) {
            return "Not enough seats available!";
        }

        double totalPrice = flight.getPrice() * noOfSeats;

        Booking booking = new Booking();
        booking.setUserDTO(userDTO); // Store user details
        booking.setFlight(flight);
        booking.setNoOfSeats(noOfSeats);
        booking.setBookingTime(LocalDateTime.now());
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.PENDING);

        bookingRepository.save(booking);

        // Update available seats
        flight.setAvailableSeats(flight.getAvailableSeats() - noOfSeats);
        flightRepository.save(flight);

        return "Booking Successful! Total Price: " + totalPrice;
    }

}
