package com.teleclimb.repositories;

import com.teleclimb.entities.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Integer> {
}
