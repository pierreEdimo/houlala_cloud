package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://authentication.houlala.store/api", name = "authentication")
public interface LoginFeignClient {

    @GetMapping("/Auth/GetUserById/{userId}")
    User getSingleUserByUserId(@PathVariable(value = "userId") String userId) throws MarketplaceException;
}
