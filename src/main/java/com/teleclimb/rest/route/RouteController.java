package com.teleclimb.rest.route;


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

    @ApiOperation(value = "Add new route", notes = "Do not insert id field, it will be generated automatically anyway. CompetitionType field is mandatory.")
    @PostMapping("")
    public void add(@RequestBody RouteDto route) {
        service.add(route);
    }

    @ApiOperation(value = "Update route", notes = "All fields can be updated apart from id and competitionType.")
    @PutMapping("/{id}")
    public void update(@RequestBody RouteDto route, @PathVariable Long id) {
        service.update(id, route);
    }

    @ApiOperation(value = "Remove competition", notes = "This operation will also remove all contestant starts on this route.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
