package com.example.authenticationclient.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends Exception {

    @Getter
    private final HttpStatus httpStatus;

    public AuthenticationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
