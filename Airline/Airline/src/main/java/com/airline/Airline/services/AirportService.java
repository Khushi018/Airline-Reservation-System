package com.airline.Airline.services;


import com.airline.Airline.models.Airport;

import jakarta.transaction.Transactional;

import java.util.List;

public interface AirportService {
    @Transactional
    Airport createAirport(Airport airport);
    Airport getAirportById(Long id);
    List<Airport> getAllAirports();
    Airport updateAirport(Long id, Airport updatedAirport);
    void deleteAirport(Long id);
    
}