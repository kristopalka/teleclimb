package com.teleclimb.rest.responses.success;

import com.teleclimb.rest.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ControllerResponse {
    public static ResponseEntity<Response> okResponse(String message) {
        HttpStatus statusHttp = HttpStatus.OK;

        Response response = new Response(message, statusHttp, statusHttp.value(), new Date());

        return new ResponseEntity<>(response, statusHttp);
    }

    public static ResponseEntity<Response> createdResponse(String message) {
        HttpStatus statusHttp = HttpStatus.CREATED;

        Response response = new Response(message, statusHttp, statusHttp.value(), new Date());

        return new ResponseEntity<>(response, statusHttp);
    }
}
