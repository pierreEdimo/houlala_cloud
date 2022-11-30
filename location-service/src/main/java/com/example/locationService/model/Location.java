package com.example.locationService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Location {


    /**
     * l'Id unique d'une Location
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * L'Id unique de l'utilisateur
     */
    private String userId;

    /**
     * Le nom de la Location
     */
    private String name;

    /**
     * La description detaillee de la location
     */
    @Column(length = 100000)
    private String description;

    /**
     * La courte description d'une location
     */
    @Column(length = 4000)
    private String shortDescription;

    /**
     * L'Id du pays ou se trouve la location
     */
    private long countryId;

    /**
     * L'Id de la categorie de la location
     */
    private long categoryId;

    /**
     * La liste des disponibilites au Cas ou le magasin est physique
     */
    @OneToMany(targetEntity = Availability.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<Availability> availabilityList;

    /**
     * L'adresse du vendeur
     */
    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    /**
     * Le numero de telephone du vendeur
     */
    private String telephoneNumber;

    /**
     * Le contact E-mail du vendeur
     */
    private String email;

    /**
     * L'adresse URl du vendeur
     */
    private String website;

    /**
     * l'Url de l'image du vendeur
     */
    private String imageUrl;

    /**
     * categorie de Location
     */
    @JsonIgnore
    private boolean isStore = false;

    /**
     * L'identifiant unique de la Location
     */
    @Column(unique = true)
    private String uniqueIdentifier;

}
