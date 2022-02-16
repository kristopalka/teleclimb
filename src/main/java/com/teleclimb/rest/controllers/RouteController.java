package com.teleclimb.rest.controllers;


import com.teleclimb.rest.dto.RouteDto;
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
    public List<RouteDto> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get route specific by id")
    @GetMapping("/{id}")
    public RouteDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @ApiOperation(value = "Add new route", notes = "Do not insert id field, it will be generated automatically anyway. CompetitionType field is mandatory. Return added route")
    @PostMapping("")
    public RouteDto add(@RequestBody RouteDto route) {
        return service.add(route);
    }

    @ApiOperation(value = "Update route", notes = "All fields can be updated apart from id and competitionType. Return updated route")
    @PutMapping("/{id}")
    public RouteDto update(@RequestBody RouteDto route, @PathVariable Long id) {
        return service.update(id, route);
    }

    @ApiOperation(value = "Remove route", notes = "This operation will also remove all contestant starts on this route.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
