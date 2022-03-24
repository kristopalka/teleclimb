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
public record CategoryService(ModelMapper mapper, CategoryRepository categoryRepo,
                              ValidationService validationService) {

    // --------------------------------- GET ---------------------------------

    public Category get(Integer id) {
        CategoryEntity category = categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found category by with: " + id));
        return toDto(category);
    }

    public List<Category> getAll() {
        return categoryRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }


    // --------------------------------- ADD ---------------------------------

    public Category add(Category category) {
        return toDto(categoryRepo.save(toEntity(category)));
    }


    // --------------------------------- MAPPING ---------------------------------

    private Category toDto(CategoryEntity entity) {
        return mapper.map(entity, Category.class);
    }

    private CategoryEntity toEntity(Category dto) {
        return mapper.map(dto, CategoryEntity.class);
    }
}
