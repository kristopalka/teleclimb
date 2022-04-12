package com.teleclimb.repositories;

import com.teleclimb.entities.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRepository extends JpaRepository<RoundEntity, Integer> {
    List<RoundEntity> findByCompetitionId(Integer competitionId);
}
