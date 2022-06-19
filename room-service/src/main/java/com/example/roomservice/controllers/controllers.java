package com.example.roomservice.controllers;

import com.example.roomservice.model.Room;
import com.example.roomservice.model.RoomOverViewDto;
import com.example.roomservice.model.RoomResponse;
import com.example.roomservice.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class controllers {

    private final RoomService roomService;

    @GetMapping("")
    public List<RoomResponse> getAllRooms() {
        return this.roomService.getAllRooms();
    }

    @PostMapping("/addRoom")
    public Room addNewRoom(@RequestBody Room newRoom) {
        return this.roomService.createRoom(newRoom);
    }

    @PutMapping("/editRoom/{id}")
    public Room editRoom(@RequestBody Room newRoom, @PathVariable long id) {
        return this.roomService.editRoom(newRoom, id);
    }

    @DeleteMapping("/deleteRoom/{id}")
    public void deleteRoom(@PathVariable long id) {
        this.roomService.deleteRoom(id);
    }

    @GetMapping("/singleRoom/{id}")
    public RoomResponse getRoom(@PathVariable long id) {
        return this.roomService.getSingleRoom(id);
    }

    @GetMapping("/getRoomsByLocationId/{id}")
    public RoomOverViewDto getRoomsByLocationId(@PathVariable String id){
        return this.roomService.getRoomsByLocationId(id);
    }

}
