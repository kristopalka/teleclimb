package com.teleclimb.rest.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "test")
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}
