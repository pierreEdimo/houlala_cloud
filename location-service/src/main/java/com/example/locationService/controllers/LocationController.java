package com.example.locationService.controllers;

import com.example.locationService.model.Location;
import com.example.locationService.model.LocationResponse;
import com.example.locationService.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService service;

    @GetMapping("/{id}")
    public LocationResponse getASingleLocation(@PathVariable long id) {
        return this.service.getLocation(id);
    }

    @PostMapping("/location")
    public Location addLocation(@RequestBody Location newLocation) {
        return this.service.createNewLocation(newLocation);
    }

    @PostMapping("/store")
    public Location createStore(@RequestBody Location newLocation) {
        return this.service.createStore(newLocation);
    }

    @GetMapping("")
    public List<LocationResponse> getAllLocations() {
        return this.service.getAllLocations();
    }

    @GetMapping("/place")
    public List<LocationResponse> getPlaces() {
        return this.service.getLocations();
    }

    @PutMapping("/{id}")
    public Location editLocation(@RequestBody Location newLocation, @PathVariable long id) {
        return this.service.editLocation(newLocation, id);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable long id) {
        this.service.deleteLocation(id);
    }

    @GetMapping("/country/{id}")
    public List<LocationResponse> getLocationByCountryId(@PathVariable long id) {
        return this.service.getLocationsByCountryId(id);
    }

    @GetMapping("/uniqueIdentifier/{uid}")
    public LocationResponse getLocationByUniqueIdentifier(@PathVariable("uid") String uniqueIdentifier) {
        return this.service.getLocationByUniqueIdentifier(uniqueIdentifier);
    }

    @GetMapping("/store")
    public List<LocationResponse> getAllStores(@RequestParam(required = false) Long limit) {
        return this.service.getStores(limit);
    }

    @GetMapping("/category/{id}")
    public List<LocationResponse> getStoresByCategoryId(@PathVariable("id") long categoryId) {
        return this.service.getStoreByCategoryId(categoryId);
    }

    @GetMapping("/search")
    public List<LocationResponse> filterStoreByName(@RequestParam String word) {
        return this.service.filterStoreByName(word);
    }

    @GetMapping("user/{userId}")
    public LocationResponse getLocationByUserId(@PathVariable String userId) {
        return this.service.getLocationByOwnerId(userId);
    }

    @GetMapping("/users/{userId}")
    public List<LocationResponse> getLocationsByUserId(@PathVariable String userId) {
        return this.service.getLocationsByOwnerId(userId);
    }
}
