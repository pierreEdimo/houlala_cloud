package com.example.orchestrator.controller;

import com.example.orchestrator.exception.OrchestratorException;
import com.example.orchestrator.feign.LocationServiceFeignClient;
import com.example.orchestrator.model.Location;
import com.example.orchestrator.model.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class OrchestratorLocationController {

    private final LocationServiceFeignClient placeServiceFeignClient;

    @GetMapping("/getASingleLocation/{id}")
    public LocationResponse getLocation(@PathVariable long id) {
        try {
            return this.placeServiceFeignClient.getSingleLocation(id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/newLocation")
    public Location createLocation(Location newLocation) {
        try {
            return this.placeServiceFeignClient.addNewLocation(newLocation);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/editLocation/{id}")
    public Location editLocation(@RequestBody Location newLocation, @PathVariable long id) {
        try {
            return this.placeServiceFeignClient.editLocation(newLocation, id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/deleteLocation/{id}")
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
