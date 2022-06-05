package com.example.orchestrator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Location {
    private Long id;

    private String name;

    private String description;

    private long countryId;

    private long categoryId;

    private List<Availability> availabilityList;

    private Address address;

    private String telephoneNumber;

    private String email;

    private String website;
}
