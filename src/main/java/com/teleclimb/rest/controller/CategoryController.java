package com.teleclimb.rest.controller;

import com.teleclimb.rest.entity.Category;
import com.teleclimb.rest.entity.Competition;
import com.teleclimb.rest.repository.CategoryRepository;
import com.teleclimb.rest.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService service;

    @GetMapping("")
    public List<Category> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Category get(@PathVariable Long id) {
        return service.get(id);
    }
}