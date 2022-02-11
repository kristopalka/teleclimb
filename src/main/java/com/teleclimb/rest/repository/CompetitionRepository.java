package com.teleclimb.rest.repository;

import com.teleclimb.rest.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
