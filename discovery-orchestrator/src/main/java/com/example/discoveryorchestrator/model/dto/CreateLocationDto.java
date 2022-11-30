package com.example.discoveryorchestrator.model.dto;

import com.example.discoveryorchestrator.model.Address;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateLocationDto {
    private String name;

    private String description;

    private String shortDescription;

    private long countryId;

    private long categoryId;

    private Address address;

    private String telephoneNumber;

    private String email;

    private String website;
}
