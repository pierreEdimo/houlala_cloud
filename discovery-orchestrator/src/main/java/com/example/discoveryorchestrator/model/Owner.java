package com.example.discoveryorchestrator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    /**
     * L'adresse E-mail du createur
     */
    private String email;

    /**
     * L'identifiant unique de l'utilisateur
     */
    private String id;

    /**
     * Le nom de famille de l'utilisateur
     */
    private String lastName;

    /**
     * Le prenom de l'utilisateur
     */
    private String firstName;
}
