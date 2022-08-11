package com.example.discoveryorchestrator.model;

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

    private String uniqueIdentifier;

    private String imageUrl;

    public Location(){}

    public Location(
            String name,
            String description,
            long countryId,
            long categoryId,
            Address address,
            String telephoneNumber,
            String email,
            String website
    ){
        this.name = name;
        this.description = description;
        this.countryId = countryId;
        this.categoryId = categoryId;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.website = website;
    }
}
