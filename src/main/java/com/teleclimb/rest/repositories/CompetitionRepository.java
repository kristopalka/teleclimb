package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Integer> {
}
