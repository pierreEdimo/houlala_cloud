package com.example.authenticationclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToken {
    private UserDto user;

    private String token;
}
