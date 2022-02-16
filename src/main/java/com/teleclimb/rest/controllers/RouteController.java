package com.teleclimb.rest.controllers;


import com.teleclimb.rest.dto.RouteRawDto;
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
    public List<RouteRawDto> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get route specific by id")
    @GetMapping("/{id}")
    public RouteRawDto get(@PathVariable Integer id) {
        return service.get(id);
    }

    @ApiOperation(value = "Add new route", notes = "Do not insert id field, it will be generated automatically anyway. CompetitionType field is mandatory. Return added route")
    @PostMapping("")
    public RouteRawDto add(@RequestBody RouteRawDto route) {
        return service.add(route);
    }

    @ApiOperation(value = "Update route", notes = "All fields can be updated apart from id and competitionType. Return updated route")
    @PutMapping("/{id}")
    public RouteRawDto update(@RequestBody RouteRawDto route, @PathVariable Integer id) {
        return service.update(id, route);
    }

    @ApiOperation(value = "Remove route", notes = "This operation will also remove all contestant starts on this route.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
