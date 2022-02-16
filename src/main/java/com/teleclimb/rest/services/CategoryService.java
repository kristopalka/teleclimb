package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.CategoryRawDto;
import com.teleclimb.rest.entities.Category;
import com.teleclimb.rest.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record CategoryService(ModelMapper mapper, CategoryRepository repo) {

    public List<CategoryRawDto> getAll() {
        return repo.findAll()
                .stream()
                .map(c -> mapper.map(c, CategoryRawDto.class))
                .collect(Collectors.toList());
    }

    public CategoryRawDto get(Integer id) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found category by with: " + id));

        return mapper.map(category, CategoryRawDto.class);
    }
}
