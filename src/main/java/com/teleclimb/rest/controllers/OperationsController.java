package com.teleclimb.rest.controllers;


import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.services.custom.OperationsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/operations")
@Api(tags = "operations")
public class OperationsController {
    private final OperationsService operationsService;

    @ApiOperation(value = "Generate rounds", notes = "Generate rounds for competition based on rounds schema. It generate, save to database and return the data")
    @PostMapping("/competition/{id}/generate-rounds")
    public List<Round> generateRounds(@PathVariable Integer id) {
        return operationsService.generateRounds(id);
    }

    @ApiOperation(value = "Generate starts", notes = "Generate starts based on StartsGenerationMethod")
    @PostMapping("/round/{id}/generate-starts")
    public void generateStarts(@PathVariable Integer id) {
        operationsService.generateStarts(id);
    }
}
