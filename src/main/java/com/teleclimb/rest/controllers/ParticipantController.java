package com.teleclimb.rest.controllers;

import com.teleclimb.rest.dto.Participant;
import com.teleclimb.rest.services.ParticipantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/participant")
@Api(tags = "participant")
public class ParticipantController {
    private final ParticipantService participantService;

    @ApiOperation(value = "Get all participants")
    @GetMapping("/all")
    public List<Participant> getAll() {
        return participantService.getAll();
    }

    @ApiOperation(value = "Get participant specific by id")
    @GetMapping("/{id}")
    public Participant get(@PathVariable Integer id) {
        return participantService.get(id);
    }

    @ApiOperation(value = "Add new participant", notes = "Do not insert id field, it will be generated automatically anyway. Competition id field is mandatory - participant is assigned to competition. Return added participant")
    @PostMapping("")
    public Participant add(@RequestBody Participant participant) {
        return participantService.add(participant);
    }

    @ApiOperation(value = "Update participant", notes = "All fields can be updated apart from id, competition and roundSequenceNumber. Return updated participant")
    @PutMapping("/{id}")
    public Participant update(@RequestBody Participant participant, @PathVariable Integer id) {
        return participantService.update(id, participant);
    }

    @ApiOperation(value = "Remove participant", notes = "This operation will also remove all participant starts.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        participantService.delete(id);
    }

}
