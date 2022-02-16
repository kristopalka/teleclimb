package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRepository extends JpaRepository<Round, Integer> {
    List<Round> findByCompetitionId(Integer competitionId);
}
