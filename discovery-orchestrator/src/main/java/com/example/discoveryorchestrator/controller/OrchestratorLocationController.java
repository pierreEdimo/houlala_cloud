package com.example.discoveryorchestrator.controller;

import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.feign.LocationServiceFeignClient;
import com.example.discoveryorchestrator.model.Location;
import com.example.discoveryorchestrator.model.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class OrchestratorLocationController {

    private final LocationServiceFeignClient placeServiceFeignClient;

    @GetMapping("/{id}")
    public LocationResponse getLocation(@PathVariable long id) {
        try {
            return this.placeServiceFeignClient.getSingleLocation(id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("")
    public Location createLocation(@RequestBody Location newLocation) {
        try {
            return this.placeServiceFeignClient.addNewLocation(newLocation);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Location editLocation(@RequestBody Location newLocation, @PathVariable long id) {
        try {
            return this.placeServiceFeignClient.editLocation(newLocation, id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable long id) {
        try {
            this.placeServiceFeignClient.deleteLocation(id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("")
    public List<LocationResponse> getAllLocations() {
        try {
            return this.placeServiceFeignClient.getAllLocations();
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
}
