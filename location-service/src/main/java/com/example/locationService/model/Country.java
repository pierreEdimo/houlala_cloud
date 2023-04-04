package com.example.locationService.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Country {
    /*
     * l'unique Id du pays
     */
    private long id;

    /**
     * le nom du pays
     */
    private String name;

    /**
     * le code du pays
     */
    private String code;
}
