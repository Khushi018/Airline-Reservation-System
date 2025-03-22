package com.airline.Airline.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.Airline.exceptions.ResourceNotFoundException;
import com.airline.Airline.models.Airport;
import com.airline.Airline.models.Flight;
import com.airline.Airline.repository.AirportRepo;
import com.airline.Airline.repository.FlightRepo;
import com.airline.Airline.services.FlightService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepo flightRepository;

    public FlightServiceImpl(FlightRepo flightRepository) {
        this.flightRepository = flightRepository;
       
    }

    @Autowired
    private AirportRepo airportRepository;

    @Override
    public Flight createFlight(Flight flight) {
        // Check if the departure and arrival airports exist
        Airport departure = airportRepository.findById(flight.getDepartureAirport().getId())
                .orElseThrow(() -> new RuntimeException("Departure airport not found"));

        Airport arrival = airportRepository.findById(flight.getArrivalAirport().getId())
                .orElseThrow(() -> new RuntimeException("Arrival airport not found"));

        flight.setDepartureAirport(departure);
        flight.setArrivalAirport(arrival);

        return flightRepository.save(flight);
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight with ID " + id + " not found"));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight updateFlight(Long id, Flight updatedFlight) {
        return flightRepository.findById(id).map(existingFlight -> {
            existingFlight.setFlightNumber(updatedFlight.getFlightNumber());
            existingFlight.setAirlineName(updatedFlight.getAirlineName());
            existingFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
            existingFlight.setArrivalAirport(updatedFlight.getArrivalAirport());
            existingFlight.setDepartureTime(updatedFlight.getDepartureTime());
            existingFlight.setArrivalTime(updatedFlight.getArrivalTime());
            existingFlight.setAvailableSeats(updatedFlight.getAvailableSeats());
            existingFlight.setPrice(updatedFlight.getPrice());
            return flightRepository.save(existingFlight);
        }).orElseThrow(() -> new RuntimeException("Flight with ID " + id + " not found"));
    }

    @Override
    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight with ID " + id + " not found"));
        flightRepository.delete(flight);
    }

    @Override
    public List<Flight> searchFlights(Long departureAirportId, Long arrivalAirportId,
            LocalDateTime departureTime, LocalDateTime arrivalTime,
            Double maxPrice) {

        List<Flight> flights = flightRepository.searchFlights(departureAirportId, arrivalAirportId,
                departureTime, arrivalTime, maxPrice);
        if (flights.isEmpty()) {
            throw new ResourceNotFoundException("No flights found for the given criteria");
        }
        return flights;
    }

}
