package com.teleclimb.controller;

import com.teleclimb.dto.model.competition_results.CompetitionResults;
import com.teleclimb.service.ResultsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/results")
@Api(tags = "result example")
public class ResultsController {
    ResultsService resultsService;

    @ApiOperation(value = "Get participant list with results", notes = "contain competition data, participants place, max round number and personal data")
    @GetMapping("/competition/{competitionId}}")
    public CompetitionResults getResults(@PathVariable Integer competitionId) {
        return resultsService.getResults(competitionId);
    }

}
