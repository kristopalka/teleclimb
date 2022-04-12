package com.teleclimb.repositories;

import com.teleclimb.entities.StartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartRepository extends JpaRepository<StartEntity, Integer> {
    List<StartEntity> findByParticipantId(Integer participantId);

    List<StartEntity> findByRefereePositionId(Integer refereePositionId);

}
