package com.iths.airtravels.service;

import org.springframework.stereotype.Service;
import com.iths.airtravels.entity.Location;
import com.iths.airtravels.repository.LocationRepository;

import java.util.Optional;

@Service
public class LocationService {

    final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

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
