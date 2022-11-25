package com.example.authenticationclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Register {
    /**
     * Le surnom de l'utilisateur
     */
    private String userName;

    /**
     * Le mot de passe de l'utilisateur
     */
    private String passWord;

    /**
     * L'adresse e-mail de l'utilisateur
     */
    private String email;

    /**
     * Le prenom de l'utilisateur
     */
    private String firstName;

    /**
     * Le nom de famille de l'utilisateur
     */
    private String lastName;

    /**
     * La rue d'habitation de l'utilisateur
     */
    private String streetName;

    /**
     * La boite postale de l'utilisateur
     */
    private String poBox;

    /**
     * La ville d'habitation de l'utilisateur
     */
    private String city;

    /**
     * Le pays d'habitation de l'utilisateur
     */
    private String country;

    /**
     * Le numero de domicile
     */
    private String houseNumber;

    /**
     * Le numero de telephone de l'utilisateur
     */
    private String phoneNumber;

}
