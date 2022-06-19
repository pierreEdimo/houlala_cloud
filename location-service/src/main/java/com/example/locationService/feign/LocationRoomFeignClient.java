package com.example.locationService.feign;

import com.example.locationService.exception.LocationServiceException;
import com.example.locationService.model.RoomOverviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "room", path = "room")
public interface LocationRoomFeignClient {

    @GetMapping("/getRoomsByLocationId/{id}")
    RoomOverviewDto getRoomsFromLocationId(@PathVariable String id) throws LocationServiceException;
}
