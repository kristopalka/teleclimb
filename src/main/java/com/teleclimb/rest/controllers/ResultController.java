package com.teleclimb.rest.controllers;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.rest.dto.results.ResultBouldering;
import com.teleclimb.rest.dto.results.ResultLead;
import com.teleclimb.rest.dto.results.ResultSpeed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/result-example")
@Api(tags = "result example")
public class ResultController {
    private final Gson gson = GsonConfig.gson();

    @ApiOperation(value = "Example of lead result")
    @GetMapping("/lead")
    public String leadExample() {
        ResultLead result = new ResultLead();
        result.setValue(21);
        result.setPlus(true);
        result.setTime(LocalTime.of(0, 2, 36, 523000000));
        return gson.toJson(result);
    }

    @ApiOperation(value = "Example of bouldering result")
    @GetMapping("/bouldering")
    public String boulderingExample() {
        ResultBouldering result = new ResultBouldering();
        result.setTries(8);
        result.setBonus(true);
        result.setTriesToBonus(4);
        result.setTop(false);
        result.setTriesToTop(null);
        return gson.toJson(result);
    }

    @ApiOperation(value = "Example of speed result")
    @GetMapping("/speed")
    public String speedExample() {
        ResultSpeed result = new ResultSpeed();
        result.setTime(LocalTime.of(0, 0, 7, 275000000));
        result.setDisqualifyingFalseStart(false);
        result.setFellOff(true);
        return gson.toJson(result);
    }

}
