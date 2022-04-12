package com.teleclimb.controllers.responses.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class TeleclimbException extends RuntimeException {
    @Getter
    private final HttpStatus status;

    public TeleclimbException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
