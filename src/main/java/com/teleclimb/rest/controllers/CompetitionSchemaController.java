package com.teleclimb.rest.controllers;

import com.teleclimb.rest.dto.CategoryDto;
import com.teleclimb.rest.services.CategoryService;
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
@RequestMapping("/competition_schemas")
@Api(tags = "competition schemas")
public class CompetitionSchemaController {
    private final CategoryService service;

    @ApiOperation(value = "Get all schemas", notes = "Competition schemas are hardcoded")
    @GetMapping("/all")
    public List<CategoryDto> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get schema by id")
    @GetMapping("/{id}")
    public CategoryDto get(@PathVariable Long id) {
        return service.get(id);
    }
}