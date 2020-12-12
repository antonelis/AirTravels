package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Flight;
import com.iths.airtravels.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/findall")
    public Iterable<Flight> findAllFlights() {
        return flightService.findAllFlights();
    }

    @GetMapping("/id/{id}")
    public Optional<Flight> findFlightById(@PathVariable Long id) {
        return flightService.findFlightById(id);
    }

    @PostMapping("/create")
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLocation(@PathVariable Long id) {
        flightService.deleteFlightById(id);
    }
}
