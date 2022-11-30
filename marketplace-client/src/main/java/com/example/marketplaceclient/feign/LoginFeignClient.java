package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://houlala_authentication:8000/api/", name = "authentication")
public interface LoginFeignClient {

    @GetMapping("/User/GetUserByEmail/{Email}")
    User getSingleUserByEmail(@PathVariable(value = "Email") String email) throws MarketplaceException;
}
