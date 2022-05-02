package com.teleclimb.controller;

import com.teleclimb.service.round.RoundManagementService;
import com.teleclimb.service.round.RoundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manage/round")
@Api(tags = "round management")
public class RoundManagementController {
    private final RoundManagementService service;
    private final RoundService roundService;

    @ApiOperation(value = "Start round", notes = "Round will be in_progress")
    @PostMapping("/{roundId}/start")
    public ResponseEntity<String> startRound(@PathVariable Integer roundId) {
        service.startRound(roundId);
        return ResponseEntity.ok("started");
    }

    @ApiOperation(value = "Finish round", notes = "Round will be finished")
    @PostMapping("/{roundId}/finish")
    public ResponseEntity<String> finishRound(@PathVariable Integer roundId) {
        service.finishRound(roundId);
        return ResponseEntity.ok("finished");
    }

    @ApiOperation(value = "Check if all scores are inserted")
    @GetMapping("/{roundId}/are-all-scores-inserted")
    public ResponseEntity<String> areAllScores(@PathVariable Integer roundId) {
        return ResponseEntity.ok(roundService.areAllScoresInserted(roundId).toString());
    }
}
