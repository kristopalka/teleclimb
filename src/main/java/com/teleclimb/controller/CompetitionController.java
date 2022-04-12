package com.teleclimb.controller;

import com.teleclimb.dto.model.Competition;
import com.teleclimb.dto.model.Round;
import com.teleclimb.service.competition.CompetitionService;
import com.teleclimb.service.round.RoundsGeneratingService;
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
    private final CompetitionService service;
    private final RoundsGeneratingService roundsGeneratingService;

    @ApiOperation(value = "Get all competitions")
    @GetMapping("/all")
    public List<Competition> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get competition specific by id")
    @GetMapping("/{id}")
    public Competition get(@PathVariable Integer id) {
        return service.get(id);
    }

    @ApiOperation(value = "Add new competition", notes = "Do not insert id field, it will be generated automatically anyway. All other fields are mandatory. Return added competition")
    @PostMapping("")
    public Competition add(@RequestBody Competition competition) {
        return service.add(competition);
    }

    @ApiOperation(value = "Update competition", notes = "Only field name can by updated after creation. Return updated competition")
    @PutMapping("/{id}")
    public Competition update(@RequestBody Competition competition, @PathVariable Integer id) {
        return service.update(id, competition);
    }

    @ApiOperation(value = "Remove competition", notes = "This operation will also remove all rounds and participants belonging to competition.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @ApiOperation(value = "Generate rounds", notes = "Generate rounds for competition based on rounds schema. It generate, save to database and return the data")
    @PostMapping("/{id}/generate-rounds")
    public List<Round> generateRounds(@PathVariable Integer id) {
        return roundsGeneratingService.generateRounds(id);
    }

}
