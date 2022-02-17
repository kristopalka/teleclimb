package com.teleclimb.rest.responses.error;


import com.teleclimb.rest.responses.Response;
import com.teleclimb.rest.responses.error.exception.TeleclimbException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = TeleclimbException.class)
    public ResponseEntity<Response> exceptionHandler(TeleclimbException exception, WebRequest request) {

        Response response = new Response(exception.getMessage(), exception.getStatus(), exception.getStatus().value(), new Date());

        return new ResponseEntity<>(response, exception.getStatus());
    }
}
