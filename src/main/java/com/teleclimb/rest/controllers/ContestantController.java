package com.teleclimb.rest.controllers;

import com.teleclimb.rest.dto.ContestantDto;
import com.teleclimb.rest.services.ContestantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contestant")
@Api(tags = "contestant")
public class ContestantController {
    private final ContestantService service;

    @ApiOperation(value = "Get all contestants")
    @GetMapping("/all")
    public List<ContestantDto> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get contestant specific by id")
    @GetMapping("/{id}")
    public ContestantDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @ApiOperation(value = "Add new contestant", notes = "Do not insert id field, it will be generated automatically anyway. Competition field is mandatory.")
    @PostMapping("")
    public void add(@RequestBody ContestantDto contestant) {
        service.add(contestant);
    }

    @ApiOperation(value = "Update competition", notes = "All fields can be updated apart from id and competition.")
    @PutMapping("/{id}")
    public void update(@RequestBody ContestantDto contestant, @PathVariable Long id) {
        service.update(id, contestant);
    }

    @ApiOperation(value = "Remove competition", notes = "This operation will also remove all contestant starts.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
