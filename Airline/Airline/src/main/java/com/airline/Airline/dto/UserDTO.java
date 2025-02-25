package com.airline.Airline.dto;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Embeddable
public class UserDTO {
    private String username;
    private String email;
    private String mobileNo;
}

