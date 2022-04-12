package com.teleclimb.controllers.responses.error.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends TeleclimbException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
