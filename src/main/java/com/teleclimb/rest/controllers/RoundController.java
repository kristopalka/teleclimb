package com.teleclimb.rest.controllers;

import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.dto.Route;
import com.teleclimb.rest.services.RoundService;
import com.teleclimb.rest.services.RouteService;
import com.teleclimb.rest.services.StartService;
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
    private final StartService startService;
    private final RouteService routeService;

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


    @ApiOperation(value = "Get routes added to the round by referee positions")
    @GetMapping("/{id}/routes")
    public List<Route> getRoutes(@PathVariable Integer id) {
        return routeService.getAllByRoundId(id);
    }

    @ApiOperation(value = "Add route to the round", notes = "Before generating starts, number of routes must be equal RoundEntity.numberOfRoutes field")
    @PostMapping("/{id}/add-route/{routeId}")
    public void addRoute(@PathVariable Integer id, @PathVariable Integer routeId) {
        roundService.addRoute(id, routeId);
    }

    @ApiOperation(value = "Remove route from the round")
    @PostMapping("/{id}/remove-route/{routeId}")
    public void removeRoute(@PathVariable Integer id, @PathVariable Integer routeId) {
        roundService.removeRoute(id, routeId);
    }
}
