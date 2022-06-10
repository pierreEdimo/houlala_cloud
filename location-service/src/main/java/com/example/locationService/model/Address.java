package com.example.locationService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String streetName;

    private String poBox;

    public Address(){}

    public Address(String city, String streetName, String poBox){
        this.city = city;
        this.streetName  = streetName;
        this.poBox = poBox;
    }
}
