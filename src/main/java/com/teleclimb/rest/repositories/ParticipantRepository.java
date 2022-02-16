package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    List<Participant> findByCompetitionId(Integer competitionId);

    List<Participant> findByCompetitionIdAndRoundSequenceNumber(Integer competitionId, Integer roundSequenceNumber);
}
