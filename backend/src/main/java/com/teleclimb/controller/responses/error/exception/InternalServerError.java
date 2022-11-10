package com.teleclimb.controller.responses.error.exception;

import org.springframework.http.HttpStatus;

public class InternalServerError extends TeleclimbException {
    public InternalServerError(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
