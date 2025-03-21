package com.airline.Airline.services;

public interface PaymentService {
    String processPayment(Long bookingId, double amountPaid);
}