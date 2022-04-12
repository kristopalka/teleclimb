package com.teleclimb.repositorie;

import com.teleclimb.entitie.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Integer> {


    List<ParticipantEntity> findByCompetitionId(Integer competitionId);

    List<ParticipantEntity> findByCompetitionIdAndTopRoundNumber(Integer competitionId, Integer roundSequenceNumber);
}
