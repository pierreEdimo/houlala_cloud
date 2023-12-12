package com.example.locationService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    /**
     * l'identifiant unique de l'adresse
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * La ville de l'habitation
     */
    private String city;

    /**
     * La rue de l'habitation
     */
    private String streetName;

    /**
     * La boite postale
     */
    private String poBox;
}
