package com.example.orchestrator.feign;

import com.example.orchestrator.model.Location;
import com.example.orchestrator.exception.OrchestratorException;
import com.example.orchestrator.model.LocationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "location", path = "location")
public interface LocationServiceFeignClient {
    @GetMapping("/getASingleLocation/{id}")
    LocationResponse getSingleLocation(@PathVariable long id) throws OrchestratorException;

    @GetMapping("")
    List<LocationResponse> getAllLocations() throws OrchestratorException;

    @PostMapping("/newLocation")
    Location addNewLocation(@RequestBody Location newLocation) throws OrchestratorException;

    @PutMapping("/editLocation/{id}")
    Location editLocation(@RequestBody Location newLocation, @PathVariable long id) throws OrchestratorException;

    @DeleteMapping("/deleteLocation/{id}")
    void deleteLocation(@PathVariable long id) throws OrchestratorException;
}
