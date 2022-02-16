package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.RoundRouteLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRouteLinkRepository extends JpaRepository<RoundRouteLink, Long> {
    List<RoundRouteLink> findByRoundId(Long roundId);
}
