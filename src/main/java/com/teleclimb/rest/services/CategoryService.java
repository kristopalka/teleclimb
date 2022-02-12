package com.teleclimb.rest.services;

import com.teleclimb.rest.entity.Category;
import com.teleclimb.rest.exceptions.NotFoundException;
import com.teleclimb.rest.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repo;

    public List<Category> getAll() {
        return repo.findAll();
    }

    public Category get(Long id) {
        return repo.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
