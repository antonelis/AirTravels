package com.iths.airtravels.service;

import org.springframework.stereotype.Service;
import com.iths.airtravels.entity.Location;
import com.iths.airtravels.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService implements ILocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location addLocation(Location location) {
        return  locationRepository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocation(Long id) {
        return locationRepository.getOne(id);
    }

    @Override
    public void deleteLocation(Location location) {
        locationRepository.delete(location);
    }

    @Override
    public Location saveLocation(Location location) {
        return  locationRepository.save(location);
    }
}