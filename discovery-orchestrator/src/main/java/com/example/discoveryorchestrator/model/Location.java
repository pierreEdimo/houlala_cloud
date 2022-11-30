package com.example.discoveryorchestrator.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private Long id;

    private String name;

    private String description;

    private String shortDescription;

    private long countryId;

    private long categoryId;

    private List<Availability> availabilityList;

    private Address address;

    private String telephoneNumber;

    private String email;

    private String website;

    private String uniqueIdentifier;

    private String imageUrl;

    public Location(
            String name,
            String description,
            long countryId,
            long categoryId,
            Address address,
            String telephoneNumber,
            String email,
            String website,
            String shortDescription
    ) {
        this.name = name;
        this.description = description;
        this.countryId = countryId;
        this.categoryId = categoryId;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.website = website;
        this.shortDescription = shortDescription;
    }
}
