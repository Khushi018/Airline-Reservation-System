package com.airline.Airline.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.airline.Airline.models.Flight;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Long>{
    // Search by flight number
    Flight findByFlightNumber(String flightNumber);

    // Search flights based on multiple criteria
    @Query("SELECT f FROM Flight f WHERE " +
           "(:departureAirportId IS NULL OR f.departureAirport.id = :departureAirportId) AND " +
           "(:arrivalAirportId IS NULL OR f.arrivalAirport.id = :arrivalAirportId) AND " +
           "(:departureTime IS NULL OR f.departureTime >= :departureTime) AND " +
           "(:arrivalTime IS NULL OR f.arrivalTime <= :arrivalTime) AND " +
           "(:maxPrice IS NULL OR f.price <= :maxPrice)")

    List<Flight> searchFlights(
        @Param("departureAirportId") Long departureAirportId,
        @Param("arrivalAirportId") Long arrivalAirportId,
        @Param("departureTime") LocalDateTime departureTime,
        @Param("arrivalTime") LocalDateTime arrivalTime,
        @Param("maxPrice") Double maxPrice
    );
}

    
