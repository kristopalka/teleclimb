package com.teleclimb.rest.controller;

import com.teleclimb.rest.entity.Competition;
import com.teleclimb.rest.repository.CompetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/competitions")
public class CompetitionController {
    private final CompetitionRepository repo;

    @GetMapping("")
    public List<Competition> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Competition getById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping("")
    public Competition add(@RequestBody Competition competition) {
        return repo.save(competition);
    }

    @PutMapping("/{id}")
    public Competition update(@RequestBody Competition newCompetition, @PathVariable Long id) {
        return repo.findById(id)
                .map(competition -> {
                    competition.setName(newCompetition.getName());
                    return repo.save(competition);
                }).orElseThrow(RuntimeException::new);
    }
}
