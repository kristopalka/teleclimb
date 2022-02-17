package com.teleclimb.rest.repositories;

import com.teleclimb.enums.Discipline;
import com.teleclimb.rest.entities.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Integer> {
    List<RouteEntity> findByDiscipline(Discipline discipline);
}
