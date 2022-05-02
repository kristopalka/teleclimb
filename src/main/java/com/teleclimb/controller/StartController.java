package com.teleclimb.controller;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.dto.model.Start;
import com.teleclimb.dto.model.score.ScoreBouldering;
import com.teleclimb.dto.model.score.ScoreLead;
import com.teleclimb.dto.model.score.ScoreSpeed;
import com.teleclimb.service.start.StartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/start")
@Api(tags = "start")
public class StartController {
    private final StartService service;
    private final Gson gson = GsonConfig.gson();

    @ApiOperation(value = "Get all starts")
    @GetMapping("/all")
    public List<Start> getAll() {
        return service.getAll();
    }


    @ApiOperation(value = "Get starts by referee position hash")
    @GetMapping("/all/by/referee-position-hash/{hash}")
    public List<Start> getByRefereePositionHash(@PathVariable String hash) {
        return service.getByRefereePositionHash(hash);
    }

    @ApiOperation(value = "Get start specific by id")
    @GetMapping("/{id}")
    public Start get(@PathVariable Integer id) {
        return service.get(id);
    }

    @ApiOperation(value = "Update sequence number", notes = "Attention: do not leave two or more starts (for one referee position) with the same sequence number")
    @PutMapping("/{id}/sequence-number")
    public Start updateSequenceNumber(@PathVariable Integer id, @RequestBody Integer sequenceNumber) {
        return service.updateSequenceNumber(id, sequenceNumber);
    }

    @ApiOperation(value = "Update score", notes = "Put score as json. Examples and correctness of result in 'result examples'")
    @PutMapping("/{id}/score")
    public Start updateResult(@PathVariable Integer id, @RequestBody String score) {
        return service.updateScoreMobileApp(id, score);
    }


    @ApiOperation(value = "Example of lead score")
    @GetMapping("/score-example/lead")
    public String leadExample() {
        ScoreLead result = new ScoreLead();
        result.setValue(21);
        result.setPlus(true);
        result.setTime(LocalTime.of(0, 2, 36, 523000000));
        return gson.toJson(result);
    }

    @ApiOperation(value = "Example of bouldering score")
    @GetMapping("/score-example/bouldering")
    public String boulderingExample() {
        ScoreBouldering result = new ScoreBouldering();
        result.setTries(8);
        result.setBonus(true);
        result.setTriesToBonus(4);
        result.setTop(false);
        result.setTriesToTop(null);
        return gson.toJson(result);
    }

    @ApiOperation(value = "Example of speed score")
    @GetMapping("/score-example/speed")
    public String speedExample() {
        ScoreSpeed result = new ScoreSpeed();
        result.setTime(LocalTime.of(0, 0, 7, 275000000));
        result.setDisqualifyingFalseStart(false);
        result.setFellOff(true);
        return gson.toJson(result);
    }

}
