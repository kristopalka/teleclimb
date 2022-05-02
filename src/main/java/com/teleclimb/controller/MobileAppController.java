package com.teleclimb.controller;

import com.teleclimb.dto.model.Start;
import com.teleclimb.service.start.StartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("mobile/")
@Api(tags = "mobile app")
public class MobileAppController {
    private final StartService startService;

    @ApiOperation(value = "Get starts by referee position hash")
    @GetMapping("/starts-by-referee-position-hash/{hash}")
    public List<Start> getByRefereePositionHash(@PathVariable String hash) {
        return startService.getByRefereePositionHash(hash);
    }

    @ApiOperation(value = "Update score", notes = "Put score as json. Examples and correctness of result in 'result examples'")
    @PutMapping("/update-start-score/{startId}/{hash}")
    public Start updateResult(@PathVariable Integer startId, @PathVariable String hash, @RequestBody String score) {
        return startService.updateScoreMobileApp(startId, score, hash);
    }
}
