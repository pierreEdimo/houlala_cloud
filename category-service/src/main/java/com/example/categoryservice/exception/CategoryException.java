package com.example.categoryservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CategoryException extends Exception{
    @Getter
    private final HttpStatus httpStatus;

    public CategoryException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
