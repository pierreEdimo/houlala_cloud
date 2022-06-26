package com.example.stockservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class StockServiceException extends Exception{
    @Getter
    private final HttpStatus httpStatus;

    public StockServiceException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
