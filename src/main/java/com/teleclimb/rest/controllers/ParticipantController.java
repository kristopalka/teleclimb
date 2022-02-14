package com.teleclimb.rest.controllers;

import com.teleclimb.rest.dto.ParticipantDto;
import com.teleclimb.rest.dto.custom.ParticipantRawDto;
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
    private final ParticipantService service;

    @ApiOperation(value = "Get all participants")
    @GetMapping("/all")
    public List<ParticipantDto> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get participant specific by id")
    @GetMapping("/{id}")
    public ParticipantDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @ApiOperation(value = "Add new participant", notes = "Do not insert id field, it will be generated automatically anyway. Competition field is mandatory.")
    @PostMapping("")
    public void add(@RequestBody ParticipantDto participant) {
        service.add(participant);
    }

    @ApiOperation(value = "Update competition", notes = "All fields can be updated apart from id and competition.")
    @PutMapping("/{id}")
    public void update(@RequestBody ParticipantDto participant, @PathVariable Long id) {
        service.update(id, participant);
    }

    @ApiOperation(value = "Remove competition", notes = "This operation will also remove all participant starts.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
