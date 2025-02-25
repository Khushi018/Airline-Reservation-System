    package com.airline.Airline.services.impl;


import com.airline.Airline.exceptions.AirportNotFoundException;
import com.airline.Airline.models.Airport;
import com.airline.Airline.repository.AirportRepo;
import com.airline.Airline.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepo airportRepo;

    @Override
    public Airport createAirport(Airport airport) {
        return airportRepo.save(airport);
    }

    @Override
    public Airport getAirportById(Long id) {
         return airportRepo.findById(id)
                .orElseThrow(() -> new AirportNotFoundException("Airport with ID " + id + " not found"));
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepo.findAll();
    }

    @Override
    public Airport updateAirport(Long id, Airport updatedAirport) {
        return airportRepo.findById(id).map(existingAirport -> {
            existingAirport.setAirportCode(updatedAirport.getAirportCode());
            existingAirport.setAirportName(updatedAirport.getAirportName());
            existingAirport.setLocation(updatedAirport.getLocation());
            return airportRepo.save(existingAirport);
        }).orElseThrow(() -> new AirportNotFoundException("Airport with ID " + id + " not found"));
    }
    

    @Override
    public void deleteAirport(Long id) {
        if (!airportRepo.existsById(id)) {
            throw new AirportNotFoundException("Airport with ID " + id + " not found");
        }
        airportRepo.deleteById(id);

    }
}
