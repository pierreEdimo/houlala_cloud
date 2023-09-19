package com.example.locationService.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {

    /**
     * L'id de la location
     */
    private Long id;

    /**
     * Le nom de la location
     */
    private String name;

    /**
     * La longue description de la location
     */
    private String description;

    /**
     * La courte description de la location
     */
    private String shortDescription;

    /**
     * L'adresse physique de la location
     */
    private Address address;

    /**
     * Le pays ou se trouve la location
     */
    private Country country;

    /**
     * La liste des disponibilites
     */
    private List<Availability> availabilityList;

    /**
     * Le numero de telephone du client
     */
    private String telephoneNumber;

    /**
     * L'adresse E-mail de la location
     */
    private String email;

    /**
     * Le lien du site internet de la location
     */
    private String website;

    /**
     * La categorie de la location
     */
    private Category category;

    /**
     * Le Dto des reviews d'une location
     */
    private ReviewResponseDto reviews;

    /**
     * Les chambres disponibles au cas ou la location est un hotel
     */
    private RoomOverviewDto availableRooms;

    /**
     * L'identifiant unique de la location
     */
    private String uniqueIdentifier;

    /**
     * L'Url de l'image de la location
     */
    private String imageUrl;

    /**
     * Le nombre total de commandes recues
     */
    private long orderTotalCount;

    /**
     * Le nombre de commandes reussies
     */
    private long orderSoldCount;

    /**
     * Le nombre de commandes annulees
     */
    private long orderCanceledCount;

    /**
     * le nombre total de produits disponibles
     */
    private long productTotalCount;

    /**
     * La personne qui a cree la location
     */
    private Owner creator;
}
