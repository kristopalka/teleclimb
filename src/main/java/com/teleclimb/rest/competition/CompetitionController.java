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
    public List<CompetitionEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CompetitionEntity get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("")
    public void add(@RequestBody CompetitionEntity competition) {
        service.add(competition);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody CompetitionEntity competition, @PathVariable Long id) {
        service.update(id, competition);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
