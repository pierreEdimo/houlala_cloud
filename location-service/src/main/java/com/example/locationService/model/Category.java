package com.example.locationService.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    /**
     * L'identifiant unique de la categorie
     */
    private Long id;

    /**
     * Le thumbnail de la categorie
     */
    private String thumbNail;

    /**
     * le nom de la categorie
     */
    private String name;
}
