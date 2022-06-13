package com.example.bookingservice.feign;

import com.example.bookingservice.exception.BookingServiceException;
import com.example.bookingservice.model.RoomResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "room", path = "room")
public interface BookingRoomFeignClient {
    @GetMapping("/singleRoom/{id}")
    RoomResponse getSingleRoom(@PathVariable long id) throws BookingServiceException;
}
