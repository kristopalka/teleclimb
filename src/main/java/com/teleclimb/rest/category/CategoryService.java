package com.teleclimb.rest.category;

import com.teleclimb.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repo;

    public List<CategoryEntity> getAll() {
        return repo.findAll();
    }

    public CategoryEntity get(Long id) {
        return repo.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
