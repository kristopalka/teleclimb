package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByCompetitionId(Long competitionId);

    List<Participant> findByCompetitionIdAndRoundSequenceNumber(Long competitionId, Integer roundSequenceNumber);
}
