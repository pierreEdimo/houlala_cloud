package com.example.authenticationclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressData {
    /**
     * L'adresse E-mail de l'utilisateur.
     */
    private String email;

    /**
     * La rue ou l'utilisateur habite.
     */
    private String streetName;

    /**
     * Le pays d'habitation de l'utilisateur.
     */
    private String country;

    /**
     * La boite postale de l'utilisateur.
     */
    private String poBox;

    /**
     * La ville d'habitation de l'utilisateur.
     */
    private String city;

    /**
     * le numero du domicile de l'utilisateur.
     */
    private String houseNumber;
}
