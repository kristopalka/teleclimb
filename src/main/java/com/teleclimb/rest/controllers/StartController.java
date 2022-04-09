package com.teleclimb.rest.controllers;

import com.teleclimb.rest.dto.Start;
import com.teleclimb.rest.services.StartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/start")
@Api(tags = "start")
public class StartController {
    private final StartService service;

    @ApiOperation(value = "Get all starts")
    @GetMapping("/all")
    public List<Start> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get starts in round by route id")
    @GetMapping("/all/by/{roundId}/and/{routeId}")
    public List<Start> getByRouteIdAndRoundId(@PathVariable Integer roundId, @PathVariable Integer routeId) {
        return service.getByRoundIdAndRouteId(roundId, routeId);
    }

    @ApiOperation(value = "Get start specific by id")
    @GetMapping("/{id}")
    public Start get(@PathVariable Integer id) {
        return service.get(id);
    }

    @ApiOperation(value = "Insert result")
    @PutMapping("/{id}/result")
    public void updateResult(@PathVariable Integer id, @RequestBody String result) {
        service.updateResult(id, result);
    }


}
