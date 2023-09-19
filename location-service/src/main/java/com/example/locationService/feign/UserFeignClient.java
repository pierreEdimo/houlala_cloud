package com.example.locationService.feign;

import com.example.locationService.exception.LocationServiceException;
import com.example.locationService.model.Owner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://authentication.houlala.store/api", name = "authentication")
public interface UserFeignClient {

    @GetMapping("/Auth/GetUserById/{userId}")
    Owner getSingleUserByUserId(@PathVariable(value = "userId") String uid) throws LocationServiceException;
}
