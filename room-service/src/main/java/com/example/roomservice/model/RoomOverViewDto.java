package com.example.roomservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomOverViewDto {
    private List<RoomResponse> rooms;

    private int totalAvailableRooms;
}
