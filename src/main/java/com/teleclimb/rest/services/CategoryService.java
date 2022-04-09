package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.Category;
import com.teleclimb.rest.entities.CategoryEntity;
import com.teleclimb.rest.repositories.CategoryRepository;
import com.teleclimb.rest.responses.error.exception.NotFoundException;
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
