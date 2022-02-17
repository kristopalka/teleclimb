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
public record CategoryService(ModelMapper mapper, CategoryRepository repo) {

    public List<Category> getAll() {
        return repo.findAll()
                .stream()
                .map(c -> mapper.map(c, Category.class))
                .collect(Collectors.toList());
    }

    public Category get(Integer id) {
        CategoryEntity category = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found category by with: " + id));

        return mapper.map(category, Category.class);
    }
}
