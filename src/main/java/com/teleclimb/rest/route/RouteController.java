package com.teleclimb.rest.route;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route")
public class RouteController {
    private final RouteService service;

    @GetMapping("")
    public List<Route> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Route get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("")
    public void add(@RequestBody Route route) {
        service.add(route);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Route route, @PathVariable Long id) {
        service.update(id, route);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
