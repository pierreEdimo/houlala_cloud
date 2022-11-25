package com.example.authenticationclient.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Login {

    /**
     * Adresse E-mail de l'utilisateur
     */
    private String email;

    /**
     * Mot de passe de l'utilisateurok
     */
    private String passWord;

}
