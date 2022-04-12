package com.teleclimb.repositorie;

import com.teleclimb.entitie.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRepository extends JpaRepository<RoundEntity, Integer> {
    List<RoundEntity> findByCompetitionId(Integer competitionId);
}
