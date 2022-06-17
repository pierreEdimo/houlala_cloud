package com.example.discoveryorchestrator.controller;

import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.feign.RoomServiceFeignClient;
import com.example.discoveryorchestrator.model.Room;
import com.example.discoveryorchestrator.model.RoomOverviewDto;
import com.example.discoveryorchestrator.model.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class OrchestratorRoomController {

    private final RoomServiceFeignClient feignClient;

    @GetMapping("")
    public List<RoomResponse> getAllRooms() {
        try {
            return this.feignClient.getAllRooms();
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/addRoom")
    public Room addRoom(@RequestBody Room newRoom){
        try {
            return this.feignClient.addNewRoom(newRoom);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/editRoom/{id}")
    public Room editRoom(@RequestBody Room newRoom,  @PathVariable long id){
        try {
            return this.feignClient.editRoom(newRoom, id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/deleteRoom/{id}")
    public void deleteRoom(@PathVariable long id){
        try {
            this.feignClient.deleteRoom(id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/getRoomsByLocationId/{id}")
    public RoomOverviewDto getRoomsByLocationId(@PathVariable long id){
        try {
            return this.feignClient.getRoomByLocationId(id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/singleRoom/{id}")
    public RoomResponse getRoom(@PathVariable long id){
        try {
            return this.feignClient.getRoom(id);
        } catch (OrchestratorException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

}
