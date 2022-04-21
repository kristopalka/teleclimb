package com.teleclimb.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "test")
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}
