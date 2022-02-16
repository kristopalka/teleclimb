package com.teleclimb.rest.controllers;


import com.teleclimb.rest.dto.custom.CompetitionWithParticipantsList;
import com.teleclimb.rest.dto.custom.CompetitionWithRoundsList;
import com.teleclimb.rest.services.CompetitionManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/competition")
@Api(tags = "competition management and custom data")
public class CompetitionManagementController {
    private final CompetitionManagementService additionalService;

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


    @ApiOperation(value = "Generate rounds for competition based on rounds schema. It generate, save to database and return the same as endpoint /competition/{id}/rounds")
    @PostMapping("/{id}/rounds/generate")
    public CompetitionWithRoundsList generateRounds(@PathVariable Long id) {
        return additionalService.generateRounds(id);
    }
}
