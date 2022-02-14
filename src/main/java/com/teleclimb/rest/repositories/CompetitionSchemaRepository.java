package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.CompetitionSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionSchemaRepository extends JpaRepository<CompetitionSchema, Long> {
}
