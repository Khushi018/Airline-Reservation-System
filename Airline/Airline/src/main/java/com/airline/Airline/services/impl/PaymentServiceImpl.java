package com.airline.Airline.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.Airline.enums.BookingStatus;
import com.airline.Airline.enums.PaymentStatus;
import com.airline.Airline.models.Booking;
import com.airline.Airline.models.Payment;
import com.airline.Airline.repository.BookRepo;
import com.airline.Airline.repository.PaymentRepository;
import com.airline.Airline.services.MailService;
import com.airline.Airline.services.PaymentService;

import jakarta.transaction.Transactional;
@Service
public class PaymentServiceImpl implements PaymentService {
   @Autowired
    private MailService mailService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookRepo bookingRepository;

    @Override
    @Transactional
    public String processPayment(Long bookingId, double amountPaid) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found!"));

        if (booking.getStatus() != BookingStatus.PENDING) {
            return "Booking is already confirmed or canceled!";
        }

        if (amountPaid < booking.getTotalPrice()) {
            return "Insufficient amount paid! Please pay the full amount.";
        }

        String transactionId = UUID.randomUUID().toString(); // Generate unique transaction ID
       
        // Create and save Payment
        Payment payment = new Payment(booking, amountPaid, transactionId, PaymentStatus.SUCCESS);
        payment.setPaymentTime(LocalDateTime.now());
        paymentRepository.save(payment);

        // Update Booking Status
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);
        System.out.print("Email: " + booking.getUserDTO().getEmail() + ", Flight: " + booking.getFlight() + ", Amount Paid: " + amountPaid);
        mailService.sendPaymentSuccessEmail(booking.getUserDTO().getEmail(), booking.getFlight(), amountPaid);

        return "Payment successful! Booking confirmed. Transaction ID: " + transactionId;
    }
}
