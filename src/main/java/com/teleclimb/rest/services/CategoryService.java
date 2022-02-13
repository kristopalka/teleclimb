package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.CategoryDto;
import com.teleclimb.rest.entities.Category;
import com.teleclimb.rest.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record CategoryService(CategoryRepository repo) {

    public List<CategoryDto> getAll() {
        return repo.findAll().stream().map(Category::toDto).collect(Collectors.toList());
    }

    public CategoryDto get(Long id) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found category by with: " + id));

        return category.toDto();
    }
}
