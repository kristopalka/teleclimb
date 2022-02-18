package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.StartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartRepository extends JpaRepository<StartEntity, Integer> {
    List<StartEntity> findByRoundId(Integer roundId);

    List<StartEntity> findByRouteId(Integer routeId);

    List<StartEntity> findByParticipantId(Integer participantId);

    List<StartEntity> findByRoundIdAndRouteId(Integer roundId, Integer routeId);

}
