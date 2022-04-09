package com.teleclimb.rest.controllers;

import com.teleclimb.rest.services.upperlevel.TestService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "test")
public class TestController {
    private final TestService service;

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/test/add-everything-and-generate-starts")
    public void addEverythingAndGenerateStarts() {
        service.addEverythingAndGenerateStarts();
    }
}
