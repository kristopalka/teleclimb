package com.teleclimb.rest.competition;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/competitions")
public class CompetitionController {
    private final CompetitionService service;

    @GetMapping("")
    public List<Competition> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Competition get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("")
    public void add(@RequestBody Competition competition) {
        service.add(competition);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Competition competition, @PathVariable Long id) {
        service.update(id, competition);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
