package com.teleclimb.controller;

import com.teleclimb.dto.model.Category;
import com.teleclimb.service.CategoryService;
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
@RequestMapping("/category")
@Api(tags = "category")
public class CategoryController {
    private final CategoryService service;

    @ApiOperation(value = "Get all categories", notes = "categories are constant")
    @GetMapping("/all")
    public List<Category> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get category by id")
    @GetMapping("/{id}")
    public Category get(@PathVariable Integer id) {
        return service.get(id);
    }
}