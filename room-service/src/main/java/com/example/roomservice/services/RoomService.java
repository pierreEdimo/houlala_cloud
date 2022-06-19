package com.example.roomservice.services;

import com.example.roomservice.model.Room;
import com.example.roomservice.model.RoomOverViewDto;
import com.example.roomservice.model.RoomResponse;

import java.util.List;

public interface RoomService {

    RoomResponse getSingleRoom(long id);

    Room editRoom(Room newRoom, long id);

    Room createRoom(Room newRoom);

    List<RoomResponse> getAllRooms();

    RoomOverViewDto getRoomsByLocationId(String id);

    void deleteRoom(long id);
}
