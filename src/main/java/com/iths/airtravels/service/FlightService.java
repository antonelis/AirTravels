package com.iths.airtravels.service;

import com.iths.airtravels.entity.Flight;
import com.iths.airtravels.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightService {

    final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight createFlight(Flight flight){
        return flightRepository.save(flight);
    }

    public Optional<Flight> findFlightById(Long id){
        return flightRepository.findById(id);
    }

    public Iterable<Flight> findAllFlights(){
        return flightRepository.findAll();
    }

    public void deleteFlightById(Long id){
        Optional<Flight> foundFlight = flightRepository.findById(id);
        flightRepository.deleteById(foundFlight.get().getId());
    }

}
