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
        return repo.findAll().stream().map(Category::toDto).collect(Collectors.toList());
    }

    public CategoryDto get(Long id) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found category by with: " + id));

        return category.toDto();
    }
}
