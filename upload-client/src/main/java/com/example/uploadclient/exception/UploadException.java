package com.example.uploadclient.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class UploadException extends Exception {

    @Getter
    private final HttpStatus httpStatus;

    public UploadException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
