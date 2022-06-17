package com.example.discoveryorchestrator.model;

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
