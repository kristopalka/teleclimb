package com.teleclimb.responses.error.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends TeleClimbException {
    public InvalidArgumentException(String message) {
        super(message, HttpStatus.NOT_ACCEPTABLE);
    }
}
