package com.example.discoveryorchestrator.controller;

import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.feign.LocationServiceFeignClient;
import com.example.discoveryorchestrator.feign.UploadServiceFeignClient;
import com.example.discoveryorchestrator.model.Location;
import com.example.discoveryorchestrator.model.LocationResponse;
import com.example.discoveryorchestrator.model.SimpleLocation;
import com.example.discoveryorchestrator.model.dto.CreateLocationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class OrchestratorLocationController {

    private final LocationServiceFeignClient placeServiceFeignClient;

    private final UploadServiceFeignClient uploadServiceFeignClient;

    @GetMapping("/{id}")
    public LocationResponse getLocation(@PathVariable long id) {
        try {
            return this.placeServiceFeignClient.getSingleLocation(id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("")
    public Location createLocation(@RequestPart String newLocation, @RequestPart MultipartFile image) {
        String imageUrl;
        Location location = this.createLocation(newLocation);

        try {
            imageUrl = this.uploadServiceFeignClient.uploadImage(image);
            location.setImageUrl(imageUrl);
            return this.placeServiceFeignClient.addNewLocation(location);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/store")
    public Location createStore(@RequestPart String newLocation, @RequestPart MultipartFile image) {
        String imageUrl;
        Location location = this.createLocation(newLocation);

        try {
            imageUrl = this.uploadServiceFeignClient.uploadImage(image);
            location.setImageUrl(imageUrl);
            return this.placeServiceFeignClient.createStore(location);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/place")
    public List<LocationResponse> getPLaces(@RequestParam(required = false) Long limit) {
        try {
            return this.placeServiceFeignClient.getPlaces(limit);
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

    @GetMapping("/users/{userId}")
    public LocationResponse getLocationsByUserId(@PathVariable String userId) {
        try {
            return this.placeServiceFeignClient.getLocationsByUserId(userId);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/simple/unique/{luid}")
    public SimpleLocation getSimpleLocationByLuid(@PathVariable String luid){
        try {
            return this.placeServiceFeignClient.getSimpleLocationByLuid(luid);
        } catch (OrchestratorException e){
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/store")
    public List<LocationResponse> getAllStores(@RequestParam(required = false) Long limit) {

        try {
            return this.placeServiceFeignClient.getAllStores(limit);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/category/{id}")
    public List<LocationResponse> getStoresByCategoryId(@PathVariable("id") long categoryId) {
        try {
            return this.placeServiceFeignClient.getStoreByCategoryId(categoryId);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/search")
    public List<LocationResponse> filterStoreByName(@RequestParam String word) {
        try {
            return this.placeServiceFeignClient.filterStoreByName(word);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/uniqueIdentifier/{uid}")
    public LocationResponse getSingleLocationByUniqueId(@PathVariable(value = "uid") String luid){
        try{
            return this.placeServiceFeignClient.getLocationByUniqueIdentifiers(luid);
        } catch (OrchestratorException e){
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }


    private String skuGenerator(String name) {
        Random rd = new Random();
        String result;
        LocalDateTime date = LocalDateTime.now();
        int year = date.getYear();
        int day = date.getDayOfMonth();
        int randomNb = rd.nextInt(4000);
        String productFirst3Chars = this.getThreeFirstChars(name);
        result = productFirst3Chars.toLowerCase() + "" + randomNb + "" + year + "" + day;
        return result;
    }

    private String getThreeFirstChars(String str) {
        return str.substring(0, Math.min(str.length(), 3));
    }

    private Location createLocation(String newLocation) {
        CreateLocationDto createLocationDto;
        String uniqueIdentifier;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            createLocationDto = objectMapper.readValue(newLocation, CreateLocationDto.class);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        Location location = new Location(
                createLocationDto.getName(),
                createLocationDto.getDescription(),
                createLocationDto.getCountryId(),
                createLocationDto.getCategoryId(),
                createLocationDto.getAddress(),
                createLocationDto.getTelephoneNumber(),
                createLocationDto.getEmail(),
                createLocationDto.getWebsite(),
                createLocationDto.getShortDescription()
        );

        uniqueIdentifier = this.skuGenerator(createLocationDto.getName());
        location.setUniqueIdentifier(uniqueIdentifier);

        return location;
    }
}
