package com.teleclimb.rest.round;

import com.teleclimb.rest.contestant.Contestant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRepository extends JpaRepository<Round, Long> {
    List<Round> findByCompetitionId(Long competitionId);
}
