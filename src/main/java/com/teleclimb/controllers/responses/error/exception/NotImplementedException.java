package com.teleclimb.controllers.responses.error.exception;

import org.springframework.http.HttpStatus;

public class NotImplementedException extends TeleclimbException {
    public NotImplementedException(String message) {
        super(message, HttpStatus.NOT_IMPLEMENTED);
    }
}
