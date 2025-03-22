
package com.airline.Airline.services;
import com.airline.Airline.models.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    Flight createFlight(Flight flight);
    Flight getFlightById(Long id);
    List<Flight> getAllFlights();
    Flight updateFlight(Long id, Flight updatedFlight);
    void deleteFlight(Long id);
    //String cancleFlight(Long id);

     List<Flight> searchFlights(Long departureAirportId, Long arrivalAirportId, 
                               LocalDateTime departureTime, LocalDateTime arrivalTime, 
                               Double maxPrice);
}
