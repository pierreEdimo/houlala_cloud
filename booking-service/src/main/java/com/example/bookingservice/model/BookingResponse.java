package com.example.bookingservice.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class BookingResponse {

    private Long id;

    private RoomResponse room;

    private LocalDateTime arrival;

    private LocalDateTime departure;

    private double totalPrice;

    private int totalRoomQuantity;

    private LocalDateTime createdAt ;
}
