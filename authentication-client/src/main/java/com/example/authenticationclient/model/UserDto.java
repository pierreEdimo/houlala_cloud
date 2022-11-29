package com.example.authenticationclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    /**
     * L'adresse E-mail de l'utilisateur
     */
    private String email;

    /**
     * L'Identifiant unique de l'utilisateur
     */
    private String id;

    /**
     * Le surnom de l'utilisateur
     */
    private String userName;

    /**
     * Le nom de Famille de l'utilisateur
     */
    private String lastName;

    /**
     * Le prenome de l'utilisateur
     */
    private String firstName;

    /**
     * La rue d'habitation de l'utilisateur
     */
    private String streetName;

    /**
     * La boite postale de l'utilisateur
     */
    private String poBox;

    /**
     * La ville d'habitation du client
     */
    private String city;

    /**
     * Le pays d'habitation du client
     */
    private String country;

    /**
     * Le numero d'habitation
     */
    private String houseNumber;

    /**
     * Le numero de telephone de l'utilisateur
     */
    private String phoneNumber;

}
