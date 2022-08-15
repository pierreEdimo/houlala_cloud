package com.example.discoveryorchestrator.feign;

import com.example.discoveryorchestrator.model.Location;
import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.model.LocationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "location", path = "location")
public interface LocationServiceFeignClient {
    @GetMapping("/{id}")
    LocationResponse getSingleLocation(@PathVariable long id) throws OrchestratorException;

    @GetMapping("")
    List<LocationResponse> getAllLocations() throws OrchestratorException;

    @PostMapping("")
    Location addNewLocation(@RequestBody Location newLocation) throws OrchestratorException;

    @PutMapping("/{id}")
    Location editLocation(@RequestBody Location newLocation, @PathVariable long id) throws OrchestratorException;

    @DeleteMapping("/{id}")
    void deleteLocation(@PathVariable long id) throws OrchestratorException;

    @GetMapping("/store")
    List<LocationResponse> getAllStores(@RequestParam(required = false) Long limit) throws OrchestratorException;

    @GetMapping("/category/{id}")
    List<LocationResponse> getStoreByCategoryId(@PathVariable("id") long categoryId) throws OrchestratorException;

    @GetMapping("/search")
    List<LocationResponse> filterStoreByName(@RequestParam String word) throws OrchestratorException;

    @GetMapping("/users/{userId}")
    List<LocationResponse> getLocationsByUserId(@PathVariable String userId) throws OrchestratorException;
}
