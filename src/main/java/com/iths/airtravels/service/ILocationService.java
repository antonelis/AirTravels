package com.iths.airtravels.service;

import com.iths.airtravels.entity.Location;

import java.util.List;

public interface ILocationService {
    Location addLocation(Location location);
    List<Location> getAllLocations();
    Location getLocation(Long id);
    void deleteLocation(Location location);
    Location saveLocation(Location location);

}
