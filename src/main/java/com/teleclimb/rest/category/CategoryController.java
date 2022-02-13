package com.teleclimb.rest.category;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
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

    @ExternalDocumentation
    @GetMapping("")
    public List<CategoryEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CategoryEntity get(@PathVariable Long id) {
        return service.get(id);
    }
}