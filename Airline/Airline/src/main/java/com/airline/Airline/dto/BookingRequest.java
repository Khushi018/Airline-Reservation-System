package com.airline.Airline.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Data
public class BookingRequest {
    private UserDTO userDTO;
    private String flightNumber;
    private int noOfSeats;
}