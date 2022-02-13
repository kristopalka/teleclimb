package com.teleclimb.rest.contestant;

import com.teleclimb.rest.competition.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestantRepository extends JpaRepository<ContestantEntity, Long> {
    List<ContestantEntity> findByCompetitionId(Competition competition);
}
