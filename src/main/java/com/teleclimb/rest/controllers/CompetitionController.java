package com.teleclimb.rest.controllers;

import com.teleclimb.rest.dto.CompetitionDto;
import com.teleclimb.rest.dto.custom.CompetitionWithParticipantsList;
import com.teleclimb.rest.dto.RoundDto;
import com.teleclimb.rest.dto.custom.CompetitionWithRoundsList;
import com.teleclimb.rest.services.CompetitionExtendedService;
import com.teleclimb.rest.services.CompetitionService;
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
    private final CompetitionExtendedService additionalService;

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

    @ApiOperation(value = "Update competition", notes = "Only field name can by updated after creation.")
    @PutMapping("/{id}")
    public void update(@RequestBody CompetitionDto competition, @PathVariable Long id) {
        service.update(id, competition);
    }

    @ApiOperation(value = "Remove competition", notes = "This operation will also remove all rounds and participants belonging to competition.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    // Extended

    @ApiOperation(value = "Return CUSTOM OBJECT: competition and participants list")
    @GetMapping("/{id}/participants")
    public CompetitionWithParticipantsList getAllParticipants(@PathVariable Long id) {
        return additionalService.getAllParticipants(id);
    }

    @ApiOperation(value = "Return CUSTOM OBJECT: competition and rounds list")
    @GetMapping("/{id}/rounds")
    public CompetitionWithRoundsList getAllRounds(@PathVariable Long id) {
        return additionalService.getAllRounds(id);
    }

    //todo add endpoint - generate rounds (potrzebuję dodatkowego pola - schemat rund? - przeczytać przepisy)
    //todo add endpoint - remove rounds
}
