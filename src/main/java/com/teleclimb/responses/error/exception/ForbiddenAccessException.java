package com.teleclimb.responses.error.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenAccessException extends TeleclimbException {
    public ForbiddenAccessException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
