package com.teleclimb.app.rest.controller;

import com.teleclimb.app.rest.entity.Category;
import com.teleclimb.app.rest.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository repo;



    @GetMapping("/category/all")
    List<Category> all() {
        Category category = new Category();
        category.setName("best");
        category.setFromAge(10);
        category.setToAge(20);
        repo.save(category);

        return repo.findAll();
    }

    @PutMapping("/category/add")
    Category add(@RequestBody Category category) {
        return repo.save(category);
    }
}
