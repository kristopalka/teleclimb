package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRepository extends JpaRepository<RoundEntity, Integer> {
    List<RoundEntity> findByCompetitionId(Integer competitionId);
}
