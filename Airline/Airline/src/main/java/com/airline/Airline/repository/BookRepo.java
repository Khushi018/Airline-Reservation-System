package com.airline.Airline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.Airline.models.Booking;

public interface BookRepo extends JpaRepository<Booking, Long> {
    Optional<Booking> findById(Long bookingId);
    void deleteById(Long bookingId);
    
}
