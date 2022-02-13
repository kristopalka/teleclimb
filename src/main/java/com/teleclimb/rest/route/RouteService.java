package com.teleclimb.rest.route;

import com.teleclimb.responses.error.exception.NotFoundException;
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
                .orElseThrow(() -> new NotFoundException("Not found route with id: " + id));
    }

    public void add(Route route) {
        repo.save(route);
    }

    public void update(Long id, Route route) {
        repo.findById(id)
                .map(r -> {
                    r.setName(route.getName());
                    r.setDescription(route.getDescription());
                    return repo.save(r);
                })
                .orElseThrow(() -> new NotFoundException("Not found route with id: " + id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
