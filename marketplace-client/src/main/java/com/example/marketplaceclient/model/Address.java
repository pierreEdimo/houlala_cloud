package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {
    private String city;
    private String country;
    private String streetName;
    private String poBox;
}
