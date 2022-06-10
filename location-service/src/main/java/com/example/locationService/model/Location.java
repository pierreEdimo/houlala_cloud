package com.example.locationService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private long countryId;

    private long categoryId;

    @OneToMany(targetEntity = Availability.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private List<Availability> availabilityList;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    private String telephoneNumber;

    private String email;

    private String website;

    public Location(){}

    public Location( String name,
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
