package com.iths.airtravels.controller;

import com.iths.airtravels.entity.*;
import com.iths.airtravels.service.FlightService;
import com.iths.airtravels.service.IUsersService;
import com.iths.airtravels.service.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/flight")
public class FlightController {

    private final IUsersService usersService;
    private final FlightService flightService;
    private final LocationService locationService;

    public FlightController(IUsersService usersService, FlightService flightService, LocationService locationService) {
        this.usersService = usersService;
        this.flightService = flightService;
        this.locationService = locationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("currentUser", getUserData());
        List<Flight> flight = flightService.findAllFlights();
        model.addAttribute("flight", flight);

        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);

        return "flightindex";
    }

    @GetMapping("/flightdetails/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("currentUser", getUserData());
        Flight flight = flightService.findFlightById(id);
        model.addAttribute("flight", flight);

        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);

        return "flightdetails";
    }

    @GetMapping("/addflight")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String addLocation(Model model){
        model.addAttribute("currentUser", getUserData());
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "addflight";
    }

    @PostMapping("/addflight")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String createFlight(@RequestParam(name = "location_id", defaultValue = "0") Long locationId,
                               @RequestParam(name = "flight_price", defaultValue = "0") BigDecimal price) {

        Location lct = locationService.getLocation(locationId);

        if (lct != null) {
            Flight flight = new Flight();
            flight.setPrice(price);
            flight.setFromLocation(lct);
            flight.setToLocation(lct);

            flightService.createFlight(flight);
        }
        return "redirect:/flight/";
    }

    @GetMapping("/editflight/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String editFlight(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("currentUser", getUserData());
        Flight flight = flightService.findFlightById(id);
        model.addAttribute("flight", flight);

        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);


        return "editflight";
    }

    @PostMapping("/saveFlight")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String saveFlight(@RequestParam(name = "id", defaultValue = "0") Long id,
                             @RequestParam(name = "fromlocation_id", defaultValue = "0") Long fromlocationId,
                             @RequestParam(name = "tolocation_id", defaultValue = "0") Long tolocationId,
                             @RequestParam(name = "flight_price", defaultValue = "0") BigDecimal price) {

        Flight flight = flightService.findFlightById(id);
        if (flight != null) {
            Location flct = locationService.getLocation(fromlocationId);
            Location tlct = locationService.getLocation(tolocationId);

            if (flct != null && tlct !=null) {
                flight.setPrice(price);
                flight.setFromLocation(flct);
                flight.setToLocation(tlct);
                flightService.saveFlight(flight);
            }
        }
        return "redirect:/flight/";
    }

    @GetMapping("/findall")
    public List<Flight> findAllFlights(Model model) {
        model.addAttribute("currentUser", getUserData());
        return flightService.findAllFlights();
    }

    @GetMapping("/id/{id}")
    public Flight findFlightById(@PathVariable Long id, Model model) {
        model.addAttribute("currentUser", getUserData());
        return flightService.findFlightById(id);
    }

    @PostMapping("/deleteflight")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public String deleteLocation(@RequestParam(name = "id", defaultValue = "0") Long id) {
        Flight flight = flightService.findFlightById(id);
        if (flight != null) {
            flightService.deleteFlight(flight);
        }
        return "redirect:/flight/";
    }

    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User)authentication.getPrincipal();
            Users myUser = usersService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }
}
