package com.teleclimb.responses.error;

import com.teleclimb.responses.Response;
import com.teleclimb.responses.error.exceptions.TeleClimbException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = TeleClimbException.class)
    public ResponseEntity<Response> exceptionHandler(TeleClimbException exception, WebRequest request) {

        Response message = new Response(exception.getMessage(), exception.getStatus(), exception.getStatus().value(), new Date());

        return new ResponseEntity<>(message, exception.getStatus());
    }
}
