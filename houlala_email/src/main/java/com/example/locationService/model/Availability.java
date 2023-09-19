package com.example.locationService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Availability {

    /**
     * L'identifiant unique de la disponibilite
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * La date
     */
    private String date;

    /**
     * L'heure d'ouverture
     */
    private Time opening;

    /**
     * L'heure de fermeture
     */
    private Time closing;

}
