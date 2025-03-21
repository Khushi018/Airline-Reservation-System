package com.airline.Airline.dto;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Data
public class PaymentRequest {
    private Long bookingId;
    private double amount;
}

