package com.teleclimb.rest.category;

import com.teleclimb.responses.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repo;

    public List<CategoryDto> getAll() {
        return repo.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public CategoryDto get(Long id) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found category by with: " + id));

        return entityToDto(category);
    }

    private CategoryDto entityToDto(Category category) {
        CategoryDto dto = new CategoryDto();

        dto.setId(category.getId());
        dto.setShortName(category.getShortName());
        dto.setName(category.getName());
        dto.setFromAge(category.getFromAge());
        dto.setToAge(category.getToAge());

        return dto;
    }
}
