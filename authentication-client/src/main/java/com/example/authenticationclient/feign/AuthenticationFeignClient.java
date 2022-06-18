package com.example.authenticationclient.feign;

import com.example.authenticationclient.exception.AuthenticationException;
import com.example.authenticationclient.model.Login;
import com.example.authenticationclient.model.Register;
import com.example.authenticationclient.model.UserDto;
import com.example.authenticationclient.model.UserToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "https://newsplace.azurewebsites.net/api/", name = "authentication")
public interface AuthenticationFeignClient {

    @PostMapping("/User/Register")
    UserToken register(@RequestBody Register model) throws AuthenticationException;

    @PostMapping("/User/Login")
    UserToken login(@RequestBody Login model) throws AuthenticationException;

    @GetMapping("/User/GetUser")
    UserDto getUser() throws AuthenticationException;

    @GetMapping("/User/GetAllUsers")
    List<UserDto> getAllUsers() throws AuthenticationException;

    @PostMapping("/User/renewPassWord                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ")
    UserToken renewPassWord(@RequestBody Login model) throws AuthenticationException;


}
