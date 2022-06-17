package com.example.discoveryorchestrator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private Long id;

    private String city;

    private String streetName;

    private String poBox;
}
