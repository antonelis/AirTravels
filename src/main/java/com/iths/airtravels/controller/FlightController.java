package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Flight;
import com.iths.airtravels.entity.Hotel;
import com.iths.airtravels.entity.Location;
import com.iths.airtravels.service.FlightService;
import com.iths.airtravels.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;
    private final LocationService locationService;

    public FlightController(FlightService flightService, LocationService locationService) {
        this.flightService = flightService;
        this.locationService = locationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Flight> flight = flightService.findAllFlights();
        model.addAttribute("flight", flight);

        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);

        return "flightindex";
    }

    @GetMapping(value = "/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        Flight flight = flightService.findFlightById(id);
        model.addAttribute("flight", flight);
        return "details";
    }

    @PostMapping("/create")
    public String createFlight(@RequestParam(name = "location_id", defaultValue = "0") Long id,
                               @RequestParam(name = "flight_price", defaultValue = "0") BigDecimal price) {

        Location lct = locationService.getLocation(id);

        if (lct != null) {
            Flight flight = new Flight();
            flight.setPrice(price);
            flight.setFromLocation(lct);
            flight.setToLocation(lct);

            flightService.createFlight(flight);
        }
        return "redirect:/flight/";
    }

    @PostMapping(value = "/saveFlight")
    public String saveLocation(@RequestParam(name = "location_id", defaultValue = "0") Long id,
                               @RequestParam(name = "flight_price", defaultValue = "0") BigDecimal price,
                               @RequestParam(name = "flight_fromLocation", defaultValue = " ") Location fromLocation,
                               @RequestParam(name = "flight_toLocation", defaultValue = " ") Location toLocation) {
        Flight flight = flightService.findFlightById(id);
        if (flight != null) {
            flight.setPrice(price);
            flight.setFromLocation(fromLocation);
            flight.setToLocation(toLocation);
        }
        return "redirect:/";
    }

    @GetMapping("/findall")
    public List<Flight> findAllFlights() {
        return flightService.findAllFlights();
    }

    @GetMapping("/id/{id}")
    public Flight findFlightById(@PathVariable Long id) {
        return flightService.findFlightById(id);
    }

    @PostMapping(value = "/deleteflight")
    public String deleteLocation(@RequestParam(name = "id", defaultValue = "0") Long id) {
        Flight flight = flightService.findFlightById(id);
        if (flight != null) {
            flightService.deleteFlight(flight);
        }
        return "redirect:/";
    }
}
