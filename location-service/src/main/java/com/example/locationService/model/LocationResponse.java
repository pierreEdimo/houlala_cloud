package com.example.locationService.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LocationResponse {

    private Long id;

    private String name;

    private String description;

    private Address address;

    private Country country;

    private List<Availability> availabilityList;

    private String telephoneNumber;

    private String email;

    private String website;

    private Category category;

    private ReviewResponseDto reviews;

    private RoomOverviewDto availableRooms;

    private String uniqueIdentifier;

    private String imageUrl;
}
