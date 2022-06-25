package com.example.inventoryservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class InventoryException extends Exception{

    @Getter
    private final HttpStatus httpStatus;

    public InventoryException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
