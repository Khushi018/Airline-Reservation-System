package com.airline.Airline.services.impl;

import java.time.LocalDateTime;
import java.util.List;

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
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // @Override
    // public List<Booking> getBookingsByUserId(Long userId) {
    //     return bookingRepository.findByUserId(userId);
    // }

    @Override
    public String cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found!"));
        booking.setStatus(BookingStatus.CANCELED);
        bookingRepository.save(booking);
        return "Booking cancelled successfully!";
    }

    

    @Override
    public String bookFlight(UserDTO userDTO, String flightNumber, int noOfSeats) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber);
        
        if (flight == null) {
            throw new RuntimeException("Flight not found!");
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
