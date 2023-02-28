package com.example.authenticationclient.controller;

import com.example.authenticationclient.exception.AuthenticationException;
import com.example.authenticationclient.feign.AuthenticationFeignClient;
import com.example.authenticationclient.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class Controller {

    private final AuthenticationFeignClient feignClient;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserToken register(@RequestBody Register model) {
        try {
            return this.feignClient.register(model);
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserToken login(@RequestBody Login model) {
        try {
            return this.feignClient.login(model);
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers(@RequestHeader("Authorization") String jwt) {
        try {
            return this.feignClient.getAllUsers("Bearer " + jwt);
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/renewPassword")
    public UserToken renewPassWord(@RequestBody Login model) {
        try {
            return this.feignClient.renewPassWord(model);
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/{email}")
    public UserDto getSingleUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String jwt) {
        try {
            return this.feignClient.getSingleUserByEmail(email, "Bearer " + jwt);
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/singleUser/{email}")
    public UserToken editPeronalData(@RequestBody PersonalData model, @PathVariable String email, @RequestHeader("Authorization") String jwt) {
        try {
            return this.feignClient.editPersonalData(model, email, "Bearer " + jwt);
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/address/{email}")
    public UserToken editAddressData(@RequestBody AddressData model, @PathVariable String email, @RequestHeader("Authorization") String jwt) {
        try {
            return this.feignClient.editAddressInfos(model, email, "Bearer " + jwt);
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/email/{email}")
    public UserToken editUserEmail(@RequestBody EditEmail model, @PathVariable String email, @RequestHeader("Authorization") String jwt) {
        try {
            return this.feignClient.editEmail(model, email, "Bearer " + jwt);
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

}
