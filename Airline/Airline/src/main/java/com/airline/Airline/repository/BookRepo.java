package com.airline.Airline.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.airline.Airline.models.Booking;

public interface BookRepo extends JpaRepository<Booking, Long> {
    Optional<Booking> findById(Long bookingId);
    void deleteById(Long bookingId);

// Corrected method name (Spring Data JPA will generate the query automatically)
    // List<Booking> findByUserId(Long userId);

    // Custom JPQL Query (Alternative)
    @Query("SELECT b FROM Booking b WHERE b.userDto.userId = :userId")
    List<Booking> getBookingsByUserId(@Param("userId") Long userId);
    
}

