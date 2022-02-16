package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.RoundRouteLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRouteLinkRepository extends JpaRepository<RoundRouteLinkEntity, Integer> {
    List<RoundRouteLinkEntity> findByRoundId(Integer roundId);

    List<RoundRouteLinkEntity> findByRoundIdAndRouteId(Integer roundId, Integer routeId);
}
