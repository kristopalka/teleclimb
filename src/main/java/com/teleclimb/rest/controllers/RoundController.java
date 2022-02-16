package com.teleclimb.rest.controllers;

import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.dto.Route;
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
    private final RoundService roundService;

    @ApiOperation(value = "Get all rounds")
    @GetMapping("/all")
    public List<Round> getAll() {
        return roundService.getAll();
    }

    @ApiOperation(value = "Get round specific by id")
    @GetMapping("/{id}")
    public Round get(@PathVariable Integer id) {
        return roundService.get(id);
    }


    @ApiOperation(value = "Get routes linked to the round")
    @GetMapping("/{id}/routes")
    public List<Route> getRoutes(@PathVariable Integer id) {
        return roundService.getRoutes(id);
    }

    @ApiOperation(value = "Link route to the round", notes = "Before generating starts, number of routes must be equal RoundEntity.numberOfRoutes field")
    @PostMapping("/{id}/route/link/{routeId}")
    public void linkRoute(@PathVariable Integer id, @PathVariable Integer routeId) {
        roundService.linkRoute(id, routeId);
    }

    @ApiOperation(value = "Unlink route from the round")
    @PostMapping("/{id}/route/unlink/{routeId}")
    public void unlinkRoute(@PathVariable Integer id, @PathVariable Integer routeId) {
        roundService.unlinkRoute(id, routeId);
    }


    //todo add endpoint - get all starts in this round
}
