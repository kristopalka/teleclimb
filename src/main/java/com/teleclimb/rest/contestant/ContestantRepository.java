package com.teleclimb.rest.contestant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestantRepository extends JpaRepository<ContestantEntity, Long> {
}
