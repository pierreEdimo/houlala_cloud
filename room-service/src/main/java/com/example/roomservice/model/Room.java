package com.example.roomservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String locationId;

    private String roomDescription;

    private int availableQuantity;

    private int priceProNight;

    private int numberOfBedRooms;

    private boolean breakFastIncluded;

    private int breakFastPrice;

    private int size;

    @ManyToOne(targetEntity = RoomCategory.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private RoomCategory category;


    public Room() {
    }

    public Room(
            String roomDescription,
            int availableQuantity,
            int priceProNight,
            RoomCategory category,
            String locationId) {
        this.category = category;
        this.roomDescription = roomDescription;
        this.availableQuantity = availableQuantity;
        this.priceProNight = priceProNight;
        this.locationId = locationId;

    }

}
