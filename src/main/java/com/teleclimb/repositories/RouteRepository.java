package com.teleclimb.repositories;

import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.entities.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Integer> {
    List<RouteEntity> findByDiscipline(Discipline discipline);
}
