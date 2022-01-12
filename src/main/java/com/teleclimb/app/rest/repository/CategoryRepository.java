package com.teleclimb.app.rest.repository;

import com.teleclimb.app.rest.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
