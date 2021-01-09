package com.iths.airtravels.service;

import com.iths.airtravels.entity.Flight;
import com.iths.airtravels.entity.Location;

import java.util.List;

public interface IFlightService {
    Flight createFlight(Flight flight);
    List<Flight> findAllFlights();
    Flight findFlightById(Long id);
    void deleteFlight(Flight flight);
    Flight saveFlight(Flight flight);

}
