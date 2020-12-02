package com.iths.airtravels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iths.airtravels.entity.Location;
import com.iths.airtravels.repository.LocationRepository;

import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Location createLocation(Location location){
        return locationRepository.save(location);
    }

    public Optional<Location> findLocationById(Long id){
        return locationRepository.findById(id);
    }

    public Iterable<Location> findAllLocations(){
        return locationRepository.findAll();
    }

    public void deleteLocationById(Long id){
        Optional<Location> foundLocation = locationRepository.findById(id);
        locationRepository.deleteById(foundLocation.get().getId());
    }
}
