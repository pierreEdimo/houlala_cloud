package com.example.marketplaceclient.feign;

import com.example.marketplaceclient.exception.MarketplaceException;
import com.example.marketplaceclient.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://houlala-login.herokuapp.com/api/", name = "authentication")
public interface LoginFeignClient {

    @GetMapping("/User/GetUserByEmail/{Email}")
    User getSingleUserByEmail(@PathVariable("Email") String email) throws MarketplaceException;
}
