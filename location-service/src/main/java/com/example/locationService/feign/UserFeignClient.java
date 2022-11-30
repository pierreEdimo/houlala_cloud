package com.example.locationService.feign;

import com.example.locationService.exception.LocationServiceException;
import com.example.locationService.model.Owner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://houlala-login.herokuapp.com/api/", name = "authentication")
public interface UserFeignClient {

    @GetMapping("/User/GetUserById/{userId}")
    Owner getSingleUserByEmail(@PathVariable(value = "userId") String uid) throws LocationServiceException;
}
