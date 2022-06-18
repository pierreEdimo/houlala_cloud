package com.example.authenticationclient.controller;

import com.example.authenticationclient.exception.AuthenticationException;
import com.example.authenticationclient.feign.AuthenticationFeignClient;
import com.example.authenticationclient.model.Login;
import com.example.authenticationclient.model.Register;
import com.example.authenticationclient.model.UserDto;
import com.example.authenticationclient.model.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class Controller {

    private final AuthenticationFeignClient feignClient;

    @GetMapping("")
    public String getHello(){
        return "Hello authentication";
    }

    @PostMapping("/register")
    public UserToken register(@RequestBody Register model) {
        try {
            return this.feignClient.register(model);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/login")
    public UserToken login(@RequestBody Login model) {
        try {
            return this.feignClient.login(model);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/singleUser")
    public UserDto getUser() {
        try {
            return this.feignClient.getUser();
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers() {
        try {
            return this.feignClient.getAllUsers();
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/renewPassword")
    public UserToken renewPassWord(@RequestBody Login model) {
        try {
            return this.feignClient.renewPassWord(model);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

}
