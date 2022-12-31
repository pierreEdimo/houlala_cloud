package com.example.authenticationclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DecodedMessage {
    /**
     * Le temps ou l'erreur s'est produite
     */
    private String timeStamp;

    /**
     * La route de l'URl
     */
    private String path;

    /**
     * Le Statuscode de l'erreur
     */
    private int status;

    /**
     * L'erreur qui a ete provoquee
     */
    private String error;

    /**
     * Le message de l'erreur.
     */
    private String message;

    /**
     * la source de l'erreur
     */
    private String source;
}
