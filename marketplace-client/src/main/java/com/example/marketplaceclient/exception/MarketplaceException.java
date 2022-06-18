package com.example.marketplaceclient.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class MarketplaceException extends Exception {
    @Getter
    private final HttpStatus httpStatus;

    public MarketplaceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
