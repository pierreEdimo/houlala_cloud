package com.example.discoveryorchestrator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {
    private long id;

    private long locationId;

    private String roomDescription;

    private int availableQuantity;

    private int priceProNight;

    private int numberOfBedRooms;

    private boolean breakFastIncluded;

    private int breakFastPrice;

    private int size;

    private RoomCategory category;

}
