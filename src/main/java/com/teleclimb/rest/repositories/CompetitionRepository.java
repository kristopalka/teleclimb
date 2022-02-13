package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
