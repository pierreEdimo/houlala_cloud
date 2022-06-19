package com.example.roomservice.repositories;

import com.example.roomservice.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findRoomsByLocationId(String id);
}
