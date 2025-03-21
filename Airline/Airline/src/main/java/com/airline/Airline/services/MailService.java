package com.airline.Airline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.airline.Airline.models.Flight;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPaymentSuccessEmail(String to, Flight flight, double amount) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Payment Successful - Flight Booking");
        message.setText("Dear Customer,\n\nYour payment for Flight " + flight + " has been successful.\nTotal Amount: $" + amount + "\n\nThank you for choosing us!");

        mailSender.send(message);
    }
}
