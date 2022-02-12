package com.teleclimb.responses.error.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenAccessException extends TeleClimbException {
    public ForbiddenAccessException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
