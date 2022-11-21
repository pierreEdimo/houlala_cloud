package com.example.authenticationclient.feign;

import com.example.authenticationclient.exception.AuthenticationException;
import com.example.authenticationclient.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://houlala_authentication:8000/api/", name = "authentication")
public interface AuthenticationFeignClient {


    @PostMapping("/User/Register")
    UserToken register(@RequestBody Register model) throws AuthenticationException;

    @PostMapping("/User/Login")
    UserToken login(@RequestBody Login model) throws AuthenticationException;

    @GetMapping("/User/GetUser")
    UserDto getUser() throws AuthenticationException;

    @GetMapping("/User/GetAllUsers")
    List<UserDto> getAllUsers(@RequestHeader("Authorization") String auth) throws AuthenticationException;

    @PostMapping("/User/renewPassWord")
    UserToken renewPassWord(@RequestBody Login model, @RequestHeader("Authorization") String auth) throws AuthenticationException;

    @GetMapping("/User/GetUserByEmail/{Email}")
    UserDto getSingleUserByEmail(@PathVariable("Email") String email, @RequestHeader("Authorization") String auth) throws AuthenticationException;

    @PutMapping("/User/editUserInformations/{Email}")
    UserToken editPersonalData(@RequestBody PersonalData model, @PathVariable("Email") String email, @RequestHeader("Authorization") String auth) throws AuthenticationException;

    @PutMapping("/User/editAddressInformations/{Email}")
    UserToken editAddressInfos(@RequestBody AddressData model, @PathVariable("Email") String email, @RequestHeader("Authorization") String auth) throws AuthenticationException;

    @PutMapping("/User/editUserEmail/{Email}")
    UserToken editEmail(@RequestBody EditEmail model, @PathVariable("Email") String email, @RequestHeader("Authorization") String auth) throws AuthenticationException;
}
