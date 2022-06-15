package com.example.locationService.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomResponse {
    private long id;
    private String description;
    private int priceProNight;
    private int availableQuantity;
    private RoomCategory roomCategory;
}
