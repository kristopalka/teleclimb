package com.teleclimb.repository;

import com.teleclimb.entitie.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Integer> {
}
