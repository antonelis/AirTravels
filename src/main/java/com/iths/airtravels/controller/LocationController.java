package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Location;
import com.iths.airtravels.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/location/")
public class LocationController {

    private final LocationService locationService;

    @GetMapping(value = "/")
    public String index(Model model) {
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);

        return "index";
    }

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/findall")
    public List<Location> findAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping("/saveLocation")
    public String saveLocation(@RequestParam(name = "id", defaultValue = "0") Long id,
                               @RequestParam(name = "location_city", defaultValue = "No City") String city,
                               @RequestParam(name = "location_country") String country) {
        Location location = locationService.getLocation(id);
        if(location!=null) {
           location.setCity(city);
           location.setCountry(country);
           locationService.saveLocation(location);
        }
        return "redirect:/location/";
    }

    @PostMapping("/deletelocation")
    public String deleteLocation(@RequestParam(name = "id", defaultValue = "0") Long id) {
        Location location = locationService.getLocation(id);
        if(location!=null) {
            locationService.deleteLocation(location);
        }
        return "redirect:/location/";
    }

    @GetMapping("/id/{id}")
    public Location findLocationById(@PathVariable Long id) {
        return locationService.getLocation(id);
    }

    @PostMapping("/create")
    public String createLocation(@RequestParam(name = "location_city", defaultValue = "No City") String city,
                                 @RequestParam(name = "location_country") String country) {
        locationService.addLocation(new Location(city, country));

        return "redirect:/location/";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        Location location = locationService.getLocation(id);
        model.addAttribute("location", location);
        return "details";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
