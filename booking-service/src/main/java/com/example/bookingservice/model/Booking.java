package com.example.bookingservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long locationId;

    private long RoomId;

    private String renterId;

    private LocalDateTime arrival;

    private LocalDateTime departure;

    private double totalPrice;

    private int totalRoomQuantity;

    private LocalDateTime createdAt = LocalDateTime.now();

}
