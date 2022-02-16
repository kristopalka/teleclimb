package com.teleclimb.rest.controllers;

import com.teleclimb.rest.dto.RoundDto;
import com.teleclimb.rest.dto.RouteDto;
import com.teleclimb.rest.services.RoundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/round")
@Api(tags = "round")
public class RoundController {
    private final RoundService service;

    @ApiOperation(value = "Get all rounds")
    @GetMapping("/all")
    public List<RoundDto> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get round specific by id")
    @GetMapping("/{id}")
    public RoundDto get(@PathVariable Long id) {
        return service.get(id);
    }


    @ApiOperation(value = "Get routes linked to the round")
    @GetMapping("/{id}/routes")
    public List<RouteDto> getRoutes(@PathVariable Long id) {
        return service.getRoutes(id);
    }

    @ApiOperation(value = "Link route to the round", notes = "Before generating starts, number of routes must be equal Round.numberOfRoutes field")
    @PostMapping("/{id}/route/link/{routeId}")
    public void linkRoute(@PathVariable Long id, @PathVariable Long routeId) {
        service.linkRoute(id, routeId);
    }

    @ApiOperation(value = "Unlink route from the round")
    @PostMapping("/{id}/route/unlink/{routeId}")
    public void unlinkRoute(@PathVariable Long id, @PathVariable Long routeId) {
        service.unlinkRoute(id, routeId);
    }


    //todo generowanie startów
    //todo add endpoint - get all starts in this round
}
