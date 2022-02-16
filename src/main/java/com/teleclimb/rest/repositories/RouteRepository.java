package com.teleclimb.rest.repositories;

import com.teleclimb.rest.entities.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Integer> {
}
