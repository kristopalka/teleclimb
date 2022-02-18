package com.teleclimb.rest.controllers;

import com.teleclimb.rest.services.custom.TestService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "test")
public class TestController {
    private final TestService testService;

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/test/weird")
    public void testWeird() {
        testService.testWeird();
    }
}
