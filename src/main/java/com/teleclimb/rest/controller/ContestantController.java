package com.teleclimb.rest.controller;

import com.teleclimb.rest.entity.Competition;
import com.teleclimb.rest.entity.Contestant;
import com.teleclimb.rest.repository.ContestantRepository;
import com.teleclimb.rest.services.ContestantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContestantController {
    private final ContestantService service;

    @GetMapping("")
    public List<Contestant> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Contestant get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("")
    public void add(@RequestBody Contestant contestant) {
        service.add(contestant);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Contestant contestant, @PathVariable Long id) {
        service.update(id, contestant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
