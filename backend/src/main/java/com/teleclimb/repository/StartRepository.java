package com.teleclimb.repository;

import com.teleclimb.entitie.StartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartRepository extends JpaRepository<StartEntity, Integer> {
    List<StartEntity> findByParticipantId(Integer participantId);

    List<StartEntity> findByRefereePositionId(Integer refereePositionId);

    List<StartEntity> findByRefereePositionIdAndParticipantId(Integer refereePositionId, Integer participantId);


}
