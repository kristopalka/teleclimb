package com.teleclimb.repository;

import com.teleclimb.entitie.RefereePositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefereePositionRepository extends JpaRepository<RefereePositionEntity, Integer> {
    List<RefereePositionEntity> findByRoundId(Integer roundId);

    List<RefereePositionEntity> findByRoundIdAndRouteId(Integer roundId, Integer routeId);

    List<RefereePositionEntity> findByRouteId(Integer routeId);

    List<RefereePositionEntity> findByHash(Integer hash);
}
