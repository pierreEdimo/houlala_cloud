package com.example.authenticationclient.feign;

import com.example.authenticationclient.exception.AuthenticationException;
import com.example.authenticationclient.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "https://houlala-login.herokuapp.com/api/", name = "authentication")
public interface AuthenticationFeignClient {

    @PostMapping("/User/Register")
    UserToken register(@RequestBody Register model) throws AuthenticationException;

    @PostMapping("/User/Login")
    UserToken login(@RequestBody Login model) throws AuthenticationException;

    @GetMapping("/User/GetUser")
    UserDto getUser() throws AuthenticationException;

    @GetMapping("/User/GetAllUsers")
    List<UserDto> getAllUsers() throws AuthenticationException;

    @PostMapping("/User/renewPassWord")
    UserToken renewPassWord(@RequestBody Login model) throws AuthenticationException;

    @GetMapping("/User/GetUserByEmail/{Email}")
    UserDto getSingleUserByEmail(@PathVariable("Email") String email) throws AuthenticationException;

    @PutMapping("/User/editUserInformations")
    UserToken editPersonalData(@RequestBody PersonalData model) throws AuthenticationException;

    @PutMapping("/User/editAddressInformations")
    UserToken editAddressInfos(@RequestBody AddressData model) throws AuthenticationException;


}
