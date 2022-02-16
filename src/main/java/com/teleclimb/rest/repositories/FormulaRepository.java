package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.FormulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepository extends JpaRepository<FormulaEntity, Integer> {
}
