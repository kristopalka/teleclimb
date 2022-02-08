package com.teleclimb.rest.controller;

import com.teleclimb.rest.entity.Category;
import com.teleclimb.rest.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository repo;

    @GetMapping("/category/all")
    List<Category> all() {
        return repo.findAll();
    }
}