package com.teleclimb.rest.responses.error.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends TeleclimbException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
