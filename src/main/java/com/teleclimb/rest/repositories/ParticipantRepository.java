package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Integer> {

    List<ParticipantEntity> findByCompetitionId(Integer competitionId);

    List<ParticipantEntity> findByCompetitionIdAndRoundSequenceNumber(Integer competitionId, Integer roundSequenceNumber);
}
