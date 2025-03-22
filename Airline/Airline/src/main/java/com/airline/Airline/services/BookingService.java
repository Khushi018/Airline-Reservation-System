package com.airline.Airline.services;

import java.util.List;

import com.airline.Airline.dto.UserDTO;
import com.airline.Airline.models.Booking;

public interface BookingService  {
    public String bookFlight(UserDTO userDTO, String flightId, int noOfSeats);
    public String cancelBooking(Long bookingId);
    public List<Booking> getAllBookings();
     public List<Booking> getBookingsByUserId(Long userId);
     
}
