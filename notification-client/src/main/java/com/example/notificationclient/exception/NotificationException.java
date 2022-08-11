package com.example.notificationclient.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotificationException extends Exception {
    @Getter
    private final HttpStatus httpStatus;

    public NotificationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
