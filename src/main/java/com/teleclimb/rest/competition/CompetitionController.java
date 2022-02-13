package com.teleclimb.rest.competition;

import com.teleclimb.rest.contestant.ContestantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/competitions")
public class CompetitionController {
    private final CompetitionService service;

    @GetMapping("")
    public List<CompetitionDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CompetitionDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("")
    public void add(@RequestBody CompetitionDto competition) {
        service.add(competition);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody CompetitionDto competition, @PathVariable Long id) {
        service.update(id, competition);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }



    @GetMapping("/{id}/contestants")
    public List<ContestantEntity> getAllContestantsForCompetition(@PathVariable Long id) {
        return service.getAllContestantForCompetition(id);
    }
}
