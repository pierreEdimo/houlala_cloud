package com.example.discoveryorchestrator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomOverviewDto {
    private List<RoomResponse> rooms;

    private int totalAvailableRooms;
}
