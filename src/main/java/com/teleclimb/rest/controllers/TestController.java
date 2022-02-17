package com.teleclimb.rest.controllers;

import com.teleclimb.rest.services.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "test")
public class TestController {
    private final RoundService roundService;
    private final CompetitionService competitionService;
    private final FormulaService formulaService;
    private final RoundRouteLinkService linkService;
    private final ParticipantService participantService;
    private final StartService startService;

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/test/weird")
    public void testWeird() {

    }
}
