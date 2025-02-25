package com.airline.Airline.services;

import com.airline.Airline.dto.UserDTO;

public interface BookingService  {
    public String bookFlight(UserDTO userDTO, Long flightId, int noOfSeats);
}
