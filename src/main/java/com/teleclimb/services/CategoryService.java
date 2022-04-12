package com.teleclimb.services;

import com.teleclimb.controllers.responses.error.exception.NotFoundException;
import com.teleclimb.dto.Category;
import com.teleclimb.entities.CategoryEntity;
import com.teleclimb.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record CategoryService(ModelMapper mapper, CategoryRepository categoryRepo) {

    // --------------------------------- GET ---------------------------------

    public Category get(Integer id) {
        CategoryEntity categoryEntity = categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found category by with: " + id));
        return mapper.map(categoryEntity, Category.class);
    }

    public List<Category> getAll() {
        return categoryRepo.findAll().stream().map(categoryEntity -> mapper.map(categoryEntity, Category.class)).collect(Collectors.toList());
    }

    // --------------------------------- ADD ---------------------------------

    public Category add(Category category) {
        CategoryEntity categoryEntity = categoryRepo.save(mapper.map(category, CategoryEntity.class));
        return mapper.map(categoryEntity, Category.class);
    }
}
