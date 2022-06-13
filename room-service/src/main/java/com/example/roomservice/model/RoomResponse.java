package com.example.roomservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomResponse {

    private long id;

    private String description;

    private RoomCategory roomCategory;

    private int priceProNight;

    private int availableQuantity;
}
