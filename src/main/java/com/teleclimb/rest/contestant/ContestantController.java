package com.teleclimb.rest.contestant;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contestant")
public class ContestantController {
    private final ContestantService service;

    @GetMapping("")
    public List<ContestantEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ContestantEntity get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("")
    public void add(@RequestBody ContestantEntity contestant) {
        service.add(contestant);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody ContestantEntity contestant, @PathVariable Long id) {
        service.update(id, contestant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
