package com.example.locationService.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class LocationServiceException extends Exception{
    @Getter
    private final HttpStatus httpStatus;

    public LocationServiceException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
