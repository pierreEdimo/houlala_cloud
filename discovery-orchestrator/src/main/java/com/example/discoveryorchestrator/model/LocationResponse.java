package com.example.discoveryorchestrator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
     * Le pays ou la location se trouve
     */
    private Country country;

    /**
     * Les ouvertures au cas ou la location a un magasin physique
     */
    private List<Availability> availabilityList;

    /**
     * Le numero de telephone de la location
     */
    private String telephoneNumber;

    /**
     * L'email adresse de la location
     */
    private String email;

    /**
     * Le site internet de la location
     */
    private String website;

    /**
     * l'adresse de la location
     */
    private Address address;

    /**
     * Les reviews connectees a la location
     */
    private LocationReviewResponse reviews;

    /**
     * La categorie de la location
     */
    private Category category;

    /**
     * Les chambres disponibles au cas ou il s'agit d'un hotel
     */
    private RoomOverviewDto availableRooms;

    /**
     * L'identifiant unique de la location
     */
    private String uniqueIdentifier;

    /**
     * L'image Url de la Location
     */
    private String imageUrl;

    /**
     * le nombre de commendes total
     */
    private long orderTotalCount;

    /**
     * Le nomme de commandes reussies
     */
    private long orderSoldCount;

    /**
     * Le nombre de commandes annulees
     */
    private long orderCanceledCount;

    /**
     * le nombre total de produits
     */
    private long productTotalCount;

    /**
     * le createur de la location
     */
    private Owner creator;
}
