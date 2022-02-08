package com.teleclimb.rest.repository;

import com.teleclimb.rest.entity.Contestant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestantRepository extends JpaRepository<Contestant, Long> {
}
