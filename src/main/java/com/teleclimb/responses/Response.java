package com.teleclimb.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class Response {
    private final String message;
    private final HttpStatus statusHttp;
    private final Integer statusCode;
    private final Date timestamp;
}
