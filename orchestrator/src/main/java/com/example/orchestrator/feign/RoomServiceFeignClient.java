package com.example.orchestrator.feign;

import com.example.orchestrator.exception.OrchestratorException;
import com.example.orchestrator.model.Room;
import com.example.orchestrator.model.RoomOverviewDto;
import com.example.orchestrator.model.RoomResponse;
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
    void deleteRoom(@PathVariable long id) throws OrchestratorException;

    @GetMapping("/singleRoom/{id}")
    RoomResponse getRoom(@PathVariable long id) throws OrchestratorException;

    @GetMapping("/getRoomsByLocationId/{id}")
    RoomOverviewDto getRoomByLocationId(@PathVariable long id) throws OrchestratorException;

}
