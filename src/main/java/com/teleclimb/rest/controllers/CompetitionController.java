package com.teleclimb.rest.controllers;

import com.teleclimb.rest.dto.Competition;
import com.teleclimb.rest.dto.Participant;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.services.CompetitionService;
import com.teleclimb.rest.services.ParticipantService;
import com.teleclimb.rest.services.RoundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/competition")
@Api(tags = "competition")
public class CompetitionController {
    private final CompetitionService competitionService;
    private final ParticipantService participantService;
    private final RoundService roundService;

    @ApiOperation(value = "Get all competitions")
    @GetMapping("/all")
    public List<Competition> getAll() {
        return competitionService.getAll();
    }

    @ApiOperation(value = "Get competition specific by id")
    @GetMapping("/{id}")
    public Competition get(@PathVariable Integer id) {
        return competitionService.get(id);
    }

    @ApiOperation(value = "Add new competition", notes = "Do not insert id field, it will be generated automatically anyway. All other fields are mandatory. Return added competition")
    @PostMapping("")
    public Competition add(@RequestBody Competition competition) {
        return competitionService.add(competition);
    }

    @ApiOperation(value = "Update competition", notes = "Only field name can by updated after creation. Return updated competition")
    @PutMapping("/{id}")
    public Competition update(@RequestBody Competition competition, @PathVariable Integer id) {
        return competitionService.update(id, competition);
    }

    @ApiOperation(value = "Remove competition", notes = "This operation will also remove all rounds and participants belonging to competition.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        competitionService.delete(id);
    }

    @ApiOperation(value = "Get all attached participants")
    @GetMapping("/{id}/participants")
    public List<Participant> getAllByCompetitionId(@PathVariable Integer id) {
        return participantService.getAllByCompetitionId(id);
    }

    @ApiOperation(value = "Get all attached rounds")
    @GetMapping("/{id}/rounds")
    public List<Round> getAllRounds(@PathVariable Integer id) {
        return roundService.getAllByCompetitionId(id);
    }
}
