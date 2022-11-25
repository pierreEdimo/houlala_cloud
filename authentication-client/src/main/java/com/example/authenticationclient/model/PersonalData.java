package com.example.authenticationclient.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalData {
    /**
     * Le nom de famille de l'utilisateur
     */
    private String lastName;

    /**
     * Le prenom de l'utilisateur
     */
    private String firstName;

    /**
     * Le surnom de l'utilisateur
     */
    private String userName;

    /**
     * L'adresse E-mail de l'utilisateur
     */
    private String email;

    /**
     * Le numero de telephone de l'utilisateur
     */
    private String phoneNumber;
}
