package com.teleclimb.rest.controller;

import com.teleclimb.responses.success.ResponseController;
import com.teleclimb.rest.entity.Competition;
import com.teleclimb.rest.services.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity add(@RequestBody Competition competition) {
        service.add(competition);
        return ResponseController.created("Added new competition");
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
