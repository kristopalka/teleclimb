package com.teleclimb.rest.repository;

import com.teleclimb.rest.entity.Category;
import com.teleclimb.rest.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
