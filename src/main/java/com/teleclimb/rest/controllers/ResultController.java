package com.teleclimb.rest.controllers;

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
@Api(tags = "result format")
public class ResultController {
    @ApiOperation(value = "Example of lead result")
    @GetMapping("/lead")
    public ResultLead leadExample() {
        ResultLead result = new ResultLead();
        result.setValue(21);
        result.setPlus(true);
        result.setTime(LocalTime.of(0, 2, 36, 523000000));
        return result;
    }

    @ApiOperation(value = "Example of bouldering result")
    @GetMapping("/bouldering")
    public ResultBouldering boulderingExample() {
        ResultBouldering result = new ResultBouldering();
        result.setBonus(true);
        result.setBonusNumberOfTries(4);
        result.setTop(false);
        result.setTopNumberOfTries(0);
        return result;
    }

    @ApiOperation(value = "Example of speed result")
    @GetMapping("/speed")
    public ResultSpeed speedExample() {
        ResultSpeed result = new ResultSpeed();
        result.setTime(LocalTime.of(0, 0, 7, 275000000));
        result.setFalseStart(false);
        result.setFinished(true);
        return result;
    }

}
