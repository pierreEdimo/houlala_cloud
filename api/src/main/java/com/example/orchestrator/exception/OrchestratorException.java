package com.example.orchestrator.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class OrchestratorException extends Exception{

    @Getter
    private final HttpStatus httpStatus;

    public OrchestratorException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus= httpStatus;
    }
}
