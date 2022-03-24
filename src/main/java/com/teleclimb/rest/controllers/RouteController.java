package com.teleclimb.rest.controllers;


import com.teleclimb.enums.Discipline;
import com.teleclimb.rest.dto.Route;
import com.teleclimb.rest.services.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route")
@Api(tags = "route")
public class RouteController {
    private final RouteService service;

    @ApiOperation(value = "Get all routes")
    @GetMapping("/all")
    public List<Route> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get all routes by discipline")
    @GetMapping("/all/by/discipline/{discipline}")
    public List<Route> getAllByDiscipline(@PathVariable Discipline discipline) {
        return service.getAllByDiscipline(discipline);
    }

    @ApiOperation(value = "Get all routes added to the round")
    @GetMapping("/all/by/{roundId}")
    public List<Route> getRoutes(@PathVariable Integer roundId) {
        return service.getAllByRoundId(roundId);
    }

    @ApiOperation(value = "Get route specific by id")
    @GetMapping("/{id}")
    public Route get(@PathVariable Integer id) {
        return service.get(id);
    }

    @ApiOperation(value = "Add new route", notes = "Do not insert id field, it will be generated automatically anyway. CompetitionType field is mandatory. Return added route")
    @PostMapping("")
    public Route add(@RequestBody Route route) {
        return service.add(route);
    }

    @ApiOperation(value = "Update route", notes = "All fields can be updated apart from id and competitionType. Return updated route")
    @PutMapping("/{id}")
    public Route update(@RequestBody Route route, @PathVariable Integer id) {
        return service.update(id, route);
    }

    @ApiOperation(value = "Remove route", notes = "This operation will also remove all contestant starts on this route.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
