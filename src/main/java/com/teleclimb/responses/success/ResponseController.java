package com.teleclimb.responses.success;

import com.teleclimb.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ResponseController {
    public static ResponseEntity<Response> ok(String msg) {
        HttpStatus statusHttp = HttpStatus.OK;
        Response message = new Response(msg, statusHttp, statusHttp.value(), new Date());

        return new ResponseEntity<>(message, statusHttp);
    }

    public static ResponseEntity<Response> created(String msg) {
        HttpStatus statusHttp = HttpStatus.CREATED;

        Response message = new Response(msg, statusHttp, statusHttp.value(), new Date());

        return new ResponseEntity<>(message, statusHttp);
    }
}
