package com.example.inventoryservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class InventoryException extends Exception {

    @Getter
    private final HttpStatus httpStatus;

    public InventoryException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
