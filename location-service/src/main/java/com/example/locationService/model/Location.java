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
    @JoinColumn(name = "place_fk", referencedColumnName = "id", nullable = true)
    private List<Availability> availabilityList;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "place_fk", referencedColumnName = "id", nullable = false)
    private Address address;

    private String telephoneNumber;

    private String email;

    private String website;

}
