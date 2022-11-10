package com.teleclimb.controller;

import com.teleclimb.dto.model.RefereePositionWithRoute;
import com.teleclimb.service.RefereePositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/referee-position")
@Api(tags = "referee position")
public class RefereePositionController {
    private final RefereePositionService refereePositionService;

    @ApiOperation(value = "Get all referee positions by round id")
    @GetMapping("/all/by/{roundId}")
    public List<RefereePositionWithRoute> getAllByRoundId(@PathVariable Integer roundId) {
        return refereePositionService.getAllWithRouteByRoundId(roundId);
    }
}
