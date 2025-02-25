package com.airline.Airline.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.airline.Airline.enums.BookingStatus;

import com.airline.Airline.dto.UserDTO;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserDTO userDto;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(nullable = false)
    private int noOfSeats;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Column(nullable = false)
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public UserDTO getUserDTO() {
        return userDto;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDto = userDTO;
    }
}


