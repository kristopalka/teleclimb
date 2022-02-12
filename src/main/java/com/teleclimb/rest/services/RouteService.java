package com.teleclimb.rest.services;

import com.teleclimb.rest.entity.Route;
import com.teleclimb.rest.exceptions.NotFoundException;
import com.teleclimb.rest.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository repo;

    public List<Route> getAll() {
        return repo.findAll();
    }

    public Route get(Long id) {
        return repo.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public void add(Route route) {
        repo.save(route);
    }

    public void update(Long id, Route route) {
        repo.findById(id)
                .map(r -> {
                    r.setName(route.getName());
                    r.setTimeLimit(route.getTimeLimit());
                    r.setTimeLimit(route.getTimeLimit());
                    return repo.save(r);
                })
                .orElseThrow(NotFoundException::new);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
