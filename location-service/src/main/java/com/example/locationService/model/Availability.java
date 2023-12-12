package com.example.locationService.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Time;
import java.time.LocalTime;

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
