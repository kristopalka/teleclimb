package com.teleclimb.controller;

import com.teleclimb.dto.model.Round;
import com.teleclimb.service.RoundService;
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

    @ApiOperation(value = "Get round by competition id and sequence number")
    @GetMapping("/by/{competitionId}/and/{sequenceNumber}")
    public Round get(@PathVariable Integer competitionId, @PathVariable Integer sequenceNumber) {
        return service.getByCompetitionIdAndSequenceNumber(competitionId, sequenceNumber);
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

}
