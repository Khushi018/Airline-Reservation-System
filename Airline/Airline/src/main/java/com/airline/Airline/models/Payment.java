package com.airline.Airline.models;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.airline.Airline.enums.PaymentStatus;

@Entity
@Getter @Setter  @AllArgsConstructor
@Data
@RequiredArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private Booking booking;  // Reference to the Booking

    private double amountPaid;
    
    private LocalDateTime paymentTime;
    
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus; // PENDING, SUCCESS, FAILED

    private String transactionId; // Unique transaction ID for tracking

    public Payment(Booking booking, double amountPaid, String transactionId, PaymentStatus paymentStatus) {
        this.booking = booking;
        this.amountPaid = amountPaid;
        this.transactionId = transactionId;
        this.paymentStatus = paymentStatus;
    }

}
