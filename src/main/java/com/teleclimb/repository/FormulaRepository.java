package com.teleclimb.repository;

import com.teleclimb.entitie.FormulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepository extends JpaRepository<FormulaEntity, Integer> {
}
