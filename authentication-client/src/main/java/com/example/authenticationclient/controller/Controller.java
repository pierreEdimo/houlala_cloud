package com.example.authenticationclient.controller;

import com.example.authenticationclient.exception.AuthenticationException;
import com.example.authenticationclient.feign.AuthenticationFeignClient;
import com.example.authenticationclient.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class Controller {

    private final AuthenticationFeignClient feignClient;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserToken register(@RequestBody Register model) {
        try {
            return this.feignClient.register(model);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
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

    @GetMapping("/{email}")
    public UserDto getSingleUserByEmail(@PathVariable String email) {
        try {
            return this.feignClient.getSingleUserByEmail(email);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/singleUser")
    public UserToken editPeronalData(@RequestBody PersonalData model) {
        try {
            return this.feignClient.editPersonalData(model);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/address")
    public UserToken editAddressData(@RequestBody AddressData model){
        try {
            return this.feignClient.editAddressInfos(model);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

}
