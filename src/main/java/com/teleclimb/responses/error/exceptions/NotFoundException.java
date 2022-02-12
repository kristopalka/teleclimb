package com.teleclimb.responses.error.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends TeleClimbException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}


