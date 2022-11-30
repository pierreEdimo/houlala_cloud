package com.example.discoveryorchestrator.feign;

import com.example.discoveryorchestrator.exception.OrchestratorException;
import com.example.discoveryorchestrator.model.Room;
import com.example.discoveryorchestrator.model.RoomOverviewDto;
import com.example.discoveryorchestrator.model.RoomResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "room", path = "room")
public interface RoomServiceFeignClient {

    @GetMapping("")
    List<RoomResponse> getAllRooms() throws OrchestratorException;

    @PostMapping("/addRoom")
    Room addNewRoom(@RequestBody Room newRoom) throws OrchestratorException;

    @PutMapping("/editRoom/{id}")
    Room editRoom(@RequestBody Room newRoom, @PathVariable long id) throws OrchestratorException;

    @DeleteMapping("/deleteRoom/{id}")
    void deleteRoom(@PathVariable(value = "id") long id) throws OrchestratorException;

    @GetMapping("/singleRoom/{id}")
    RoomResponse getRoom(@PathVariable(value = "id") long id) throws OrchestratorException;

    @GetMapping("/getRoomsByLocationId/{id}")
    RoomOverviewDto getRoomByLocationId(@PathVariable("id") long id) throws OrchestratorException;

}
