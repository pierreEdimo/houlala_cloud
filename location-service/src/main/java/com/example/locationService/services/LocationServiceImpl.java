package com.example.locationService.services;

import com.example.locationService.exception.LocationServiceException;
import com.example.locationService.feign.LocationCategoryFeignClient;
import com.example.locationService.feign.LocationCountryFeignClient;
import com.example.locationService.feign.LocationReviewFeignClient;
import com.example.locationService.feign.LocationRoomFeignClient;
import com.example.locationService.model.*;
import com.example.locationService.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;

    private final LocationCountryFeignClient client;

    private final LocationCategoryFeignClient categoryClient;

    private final LocationReviewFeignClient reviewFeignClient;

    private final LocationRoomFeignClient roomFeignClient;

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
    public Location createNewLocation(Location location) {
        return this.repository.save(location);
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
                throw new RuntimeException(e);
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
                throw new RuntimeException(e);
            }
        }

        return existingLocations;
    }


    private LocationResponse toLocationResponse(Location location) throws LocationServiceException {

        Country countryLocation = this.client.getASingleCountry(location.getCountryId());
        Category categoryLocation = this.categoryClient.getSingleCategory(location.getCategoryId());
        ReviewResponseDto reviews = this.reviewFeignClient.getReviewsByLocation(Math.toIntExact(location.getId()));
        RoomOverviewDto rooms = this.roomFeignClient.getRoomsFromLocationId(location.getId());

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

        if (response.getCategory().getName().equals("Hotel")) {
            response.setAvailableRooms(rooms);
        }

        return response;
    }
}

