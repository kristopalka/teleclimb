package com.teleclimb.rest.competition;

import com.teleclimb.rest.contestant.Contestant;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/competition")
public class CompetitionController {
    private final CompetitionService service;

    @ApiOperation(value = "Get all competitions")
    @GetMapping("/all")
    public List<CompetitionDto> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get competition specific by id")
    @GetMapping("/{id}")
    public CompetitionDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @ApiOperation(value = "Add new competition", notes = "Do not insert id field, it will be generated automatically anyway. All other fields are mandatory.")
    @PostMapping("")
    public void add(@RequestBody CompetitionDto competition) {
        service.add(competition);
    }

    @ApiOperation(value = "Update competition", notes = "Only name can by updated after creation.")
    @PutMapping("/{id}")
    public void update(@RequestBody CompetitionDto competition, @PathVariable Long id) {
        service.update(id, competition);
    }

    @ApiOperation(value = "Remove competition", notes = "This operation will also remove all rounds and contestants belonging to competition.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @ApiOperation(value = "Get all contestants, belonging to this competition")
    @GetMapping("/{id}/contestants")
    public List<Contestant> getAllContestantsForCompetition(@PathVariable Long id) {
        return service.getAllContestantForCompetition(id);
    }
}
