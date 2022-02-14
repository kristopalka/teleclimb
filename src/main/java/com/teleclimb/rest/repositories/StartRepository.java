package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.Start;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartRepository extends JpaRepository<Start, Long> {
    List<Start> findByRoundId(Long roundId);

    List<Start> findByRouteId(Long routeId);

    List<Start> findByParticipantId(Long participantId);
}
