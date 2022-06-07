package com.example.locationService.controllers;

import com.example.locationService.model.Location;
import com.example.locationService.model.LocationResponse;
import com.example.locationService.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final LocationService service;

    @GetMapping("/getASingleLocation/{id}")
    public LocationResponse getASingleLocation(@PathVariable long id) {
        return this.service.getLocation(id);
    }

    @PostMapping("/newLocation")
    public Location addLocation(@RequestBody Location newLocation) {
        return this.service.createNewLocation(newLocation);
    }

    @GetMapping("")
    public List<LocationResponse> getAllLocations() {
        return this.service.getAllLocations();
    }

    @PutMapping("/editLocation/{id}")
    public Location editLocation(@RequestBody Location newLocation, @PathVariable long id) {
        return this.service.editLocation(newLocation, id);
    }

    @DeleteMapping("/deleteLocation/{id}")
    public void deleteLocation(@PathVariable long id) {
        this.service.deleteLocation(id);
    }

    @GetMapping("/getLocationByCountryId/{id}")
    public List<LocationResponse> getLocationByCountryId(@PathVariable long id) {
        return this.service.getLocationsByCountryId(id);
    }
}
