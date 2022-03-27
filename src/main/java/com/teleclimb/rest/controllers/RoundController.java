package com.teleclimb.rest.controllers;

import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.services.RoundService;
import com.teleclimb.rest.services.upperlevel.GeneratingService;
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
    private final GeneratingService generatingService;

    @ApiOperation(value = "Get all rounds")
    @GetMapping("/all")
    public List<Round> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get all rounds by competition id")
    @GetMapping("/all/by/{competitionId}")
    public List<Round> getAllRounds(@PathVariable Integer competitionId) {
        return service.getAllByCompetitionId(competitionId);
    }

    @ApiOperation(value = "Get round specific by id")
    @GetMapping("/{id}")
    public Round get(@PathVariable Integer id) {
        return service.get(id);
    }


    @ApiOperation(value = "Add route to the round", notes = "Before generating starts, number of routes must be equal RoundEntity.numberOfRoutes field")
    @PostMapping("/{id}/add-route/{routeId}")
    public void addRoute(@PathVariable Integer id, @PathVariable Integer routeId) {
        service.addRoute(id, routeId);
    }

    @ApiOperation(value = "Remove route from the round")
    @PostMapping("/{id}/remove-route/{routeId}")
    public void removeRoute(@PathVariable Integer id, @PathVariable Integer routeId) {
        service.removeRoute(id, routeId);
    }

    @ApiOperation(value = "Generate starts", notes = "Generate starts based on StartsGenerationMethod")
    @PostMapping("/{id}/generate-starts")
    public void generateStarts(@PathVariable Integer id) {
        generatingService.generateStarts(id);
    }
}
