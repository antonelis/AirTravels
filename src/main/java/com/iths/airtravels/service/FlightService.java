package com.iths.airtravels.service;

import com.iths.airtravels.entity.Flight;
import com.iths.airtravels.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService implements IFlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findFlightById(Long id) {
        return flightRepository.getOne(id);
    }

    @Override
    public void deleteFlight(Flight flight) {
        flightRepository.delete(flight);
    }

    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }
}
