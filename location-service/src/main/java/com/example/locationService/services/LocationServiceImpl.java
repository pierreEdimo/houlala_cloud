package com.example.locationService.services;

import com.example.locationService.exception.LocationServiceException;
import com.example.locationService.feign.*;
import com.example.locationService.model.*;
import com.example.locationService.repositories.LocationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;

    private final LocationCountryFeignClient client;

    private final LocationCategoryFeignClient categoryClient;

    private final LocationReviewFeignClient reviewFeignClient;

    private final LocationRoomFeignClient roomFeignClient;

    private final UploadServiceFeignClient uploadServiceFeignClient;

    @Override
    public LocationResponse getLocation(long id) {

        Optional<Location> location = this.repository.findById(id);

        if (location.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }

        Location existingLocation = location.get();

        try {
            return this.toLocationResponse(existingLocation);
        } catch (LocationServiceException ex) {
            throw new ResponseStatusException(ex.getHttpStatus(), ex.getMessage());
        }
    }

    @Override
    public Location createNewLocation(String location, MultipartFile file) {
        Location newLocation;

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            newLocation = objectMapper.readValue(location, Location.class);
            String imageUrl = this.uploadServiceFeignClient.uploadFile(file);
            newLocation.setImageUrl(imageUrl);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return this.repository.save(newLocation);
    }

    @Override
    public List<LocationResponse> getAllLocations() {
        List<LocationResponse> allLocations = new ArrayList<>();
        Iterable<Location> locationIterable;

        locationIterable = this.repository.findAll();

        locationIterable.forEach(location -> {
            try {
                allLocations.add(this.toLocationResponse(location));
            } catch (LocationServiceException e) {
                throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
            }
        });

        return allLocations;
    }

    @Override
    public Location editLocation(Location newLocation, long id) {
        Optional<Location> location = this.repository.findById(id);

        if (location.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found");
        }

        Location existingLocation = location.get();

        existingLocation.setName(newLocation.getName());
        existingLocation.setEmail(newLocation.getEmail());
        existingLocation.setDescription(newLocation.getDescription());
        existingLocation.setWebsite(newLocation.getWebsite());
        existingLocation.setAddress(newLocation.getAddress());
        existingLocation.setAvailabilityList(newLocation.getAvailabilityList());
        existingLocation.setCountryId(newLocation.getCountryId());
        existingLocation.setCategoryId(newLocation.getCategoryId());
        existingLocation.setTelephoneNumber(newLocation.getTelephoneNumber());

        return this.repository.save(existingLocation);
    }

    @Override
    public void deleteLocation(long id) {
        Optional<Location> location = this.repository.findById(id);

        if (location.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location doesn't exist");
        }

        Location existingLocation = location.get();

        this.repository.delete(existingLocation);
    }

    @Override
    public List<LocationResponse> getLocationsByCountryId(long id) {
        List<LocationResponse> existingLocations = new ArrayList<>();


        List<Location> locations = this.repository.findLocationByCountryId(id);

        for (Location location : locations) {
            try {
                existingLocations.add(this.toLocationResponse(location));
            } catch (LocationServiceException e) {
                throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
            }
        }

        return existingLocations;
    }

    @Override
    public LocationResponse getLocationByUniqueIdentifier(String uniqueIdentifier) {
        Optional<Location> locationOptional = this.repository.findLocationByUniqueIdentifier(uniqueIdentifier);

        if (locationOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location doesn't exist");
        }

        try {
            return this.toLocationResponse(locationOptional.get());
        } catch (LocationServiceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @Override
    public List<LocationResponse> getStores(Long limit) {
        List<LocationResponse> locationResponses = new ArrayList<>();

        List<Location> locationsList = this.repository.getStore();


        locationsList.forEach(location -> {
            try {
                locationResponses.add(this.toLocationResponse(location));
            } catch (LocationServiceException e) {
                throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
            }
        });

        if (limit != null) {
            Collections.shuffle(locationResponses);
            return locationResponses.stream().limit(limit).collect(Collectors.toList());
        } else {
            return locationResponses;
        }
    }

    @Override
    public List<LocationResponse> getStoreByCategoryId(long categoryId) {
        List<LocationResponse> locationResponses = new ArrayList<>();

        List<Location> locationList = this.repository.getStoreByCategoryId(categoryId);

        locationList.forEach(location -> {
            try {
                locationResponses.add(this.toLocationResponse(location));
            } catch (LocationServiceException e) {
                throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
            }
        });

        return locationResponses;
    }

    @Override
    public List<LocationResponse> getLocations() {
        List<LocationResponse> locationResponses = new ArrayList<>();

        List<Location> locationList = this.repository.getLocations();

        locationList.forEach(location -> {
            try {
                locationResponses.add(this.toLocationResponse(location));
            } catch (LocationServiceException e) {
                throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
            }
        });

        return locationResponses;
    }

    @Override
    public List<LocationResponse> filterStoreByName(String searchWord) {
        List<LocationResponse> locationResponses = new ArrayList<>();

        List<Location> locations = this.repository.getStore().stream().filter(location -> location.getName().toLowerCase().contains(searchWord.toLowerCase())).toList();

        locations.forEach(location -> {
            try {
                locationResponses.add(this.toLocationResponse(location));
            } catch (LocationServiceException e) {
                throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
            }
        });

        return locationResponses;
    }


    private LocationResponse toLocationResponse(Location location) throws LocationServiceException {

        Country countryLocation = this.client.getASingleCountry(location.getCountryId());
        Category categoryLocation = this.categoryClient.getSingleCategory(location.getCategoryId());
        ReviewResponseDto reviews = this.reviewFeignClient.getReviewsByLocation(location.getUniqueIdentifier());
        RoomOverviewDto rooms = this.roomFeignClient.getRoomsFromLocationId(location.getUniqueIdentifier());

        LocationResponse response = new LocationResponse();

        response.setName(location.getName());
        response.setDescription(location.getDescription());
        response.setEmail(location.getEmail());
        response.setAvailabilityList(location.getAvailabilityList());
        response.setId(location.getId());
        response.setWebsite(location.getWebsite());
        response.setCountry(countryLocation);
        response.setTelephoneNumber(location.getTelephoneNumber());
        response.setCategory(categoryLocation);
        response.setReviews(reviews);
        response.setAddress(location.getAddress());
        response.setUniqueIdentifier(location.getUniqueIdentifier());
        response.setImageUrl(location.getImageUrl());


        if (response.getCategory().getName().equals("Hotel")) {
            response.setAvailableRooms(rooms);
        }

        return response;
    }
}

