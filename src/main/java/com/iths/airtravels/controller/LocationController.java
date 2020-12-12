package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Location;
import com.iths.airtravels.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/findall")
    public Iterable<Location> findAllLocations() {
        return locationService.findAllLocations();
    }

    @GetMapping("/id/{id}")
    public Optional<Location> findLocationById(@PathVariable Long id) {
        return locationService.findLocationById(id);
    }

    @PostMapping("/create")
    public Location createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocationById(id);
    }
}
