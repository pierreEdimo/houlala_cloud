package com.example.locationService.services;

import com.example.locationService.model.Location;
import com.example.locationService.model.LocationResponse;

import java.util.List;

public interface LocationService {

    LocationResponse getLocation(long id);

    Location createNewLocation(Location location);

    List<LocationResponse> getAllLocations();

    Location editLocation(Location location, long id);

    void deleteLocation(long id);
}
