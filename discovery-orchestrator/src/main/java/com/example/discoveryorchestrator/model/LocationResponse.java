package com.example.discoveryorchestrator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LocationResponse {
    private Long id;

    private String name;

    private String description;

    private Country country;

    private List<Availability> availabilityList;

    private String telephoneNumber;

    private String email;

    private String website;

    private Address address;

    private LocationReviewResponse reviews;

    private Category category;

    private RoomOverviewDto availableRooms;
}
