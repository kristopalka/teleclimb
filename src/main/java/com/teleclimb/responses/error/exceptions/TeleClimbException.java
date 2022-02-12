package com.teleclimb.responses.error.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class TeleClimbException extends RuntimeException {
    @Getter
    private final HttpStatus status;

    public TeleClimbException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
