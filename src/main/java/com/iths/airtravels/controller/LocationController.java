package com.iths.airtravels.controller;

import com.iths.airtravels.entity.Location;
import com.iths.airtravels.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;

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
