package com.airline.Airline.services;


import com.airline.Airline.models.Airport;

import java.util.List;

public interface AirportService {
    Airport createAirport(Airport airport);
    Airport getAirportById(Long id);
    List<Airport> getAllAirports();
    Airport updateAirport(Long id, Airport updatedAirport);
    void deleteAirport(Long id);
}
