package com.example.authenticationclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressData {
    private String email;
    private String streetName;
    private String country;
    private String poBox;
    private String city;
    private String houseNumber;
}
