package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.Start;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartRepository extends JpaRepository<Start, Integer> {
    List<Start> findByRoundId(Integer roundId);

    List<Start> findByRouteId(Integer routeId);

    List<Start> findByParticipantId(Integer participantId);
}
