package com.example.authenticationclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class
UserToken {

    /**
     * Le surnom de l'utilisateur
     */
    private String userName;

    /**
     * L'adresse E-mail de l'utilisateur
     */
    private String email;

    /**
     * L'Id de l'utilisateur
     */
    private String userId;

    /**
     * Le Token issue apres une authentication en succes
     */
    private String token;
}
