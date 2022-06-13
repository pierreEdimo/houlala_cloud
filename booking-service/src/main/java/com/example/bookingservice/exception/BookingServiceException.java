package com.example.bookingservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BookingServiceException extends Exception {

    @Getter
    private final HttpStatus httpStatus;

    public BookingServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
