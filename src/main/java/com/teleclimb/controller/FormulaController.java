package com.teleclimb.controller;

import com.teleclimb.dto.model.Formula;
import com.teleclimb.service.FormulaService;
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
@RequestMapping("/formula")
@Api(tags = "formula")
public class FormulaController {
    private final FormulaService service;

    @ApiOperation(value = "Get all formulas", notes = "Competition formulas are hardcoded")
    @GetMapping("/all")
    public List<Formula> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get formula by id")
    @GetMapping("/{id}")
    public Formula get(@PathVariable Integer id) {
        return service.get(id);
    }
}