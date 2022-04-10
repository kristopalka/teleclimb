package com.teleclimb.rest.controllers;

import com.teleclimb.rest.services.upperlevel.RoundManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/round-management")
@Api(tags = "round management")
public class RoundManagementController {
    private final RoundManagementService service;

    @ApiOperation(value = "Start round", notes = "Round will be in progress")
    @PostMapping("/{id}/start")
    public void startRound(@PathVariable Integer id) {
        service.startRound(id);
    }

    @ApiOperation(value = "Finish round", notes = "Round will be finished")
    @PostMapping("/{id}/finish")
    public void finishRound(@PathVariable Integer id) {
        service.finishRound(id);
    }
}
