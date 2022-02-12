package com.teleclimb.rest.route;

import com.teleclimb.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository repo;

    public List<RouteEntity> getAll() {
        return repo.findAll();
    }

    public RouteEntity get(Long id) {
        return repo.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public void add(RouteEntity route) {
        repo.save(route);
    }

    public void update(Long id, RouteEntity route) {
        repo.findById(id)
                .map(r -> {
                    r.setName(route.getName());
                    r.setDescription(route.getDescription());
                    return repo.save(r);
                })
                .orElseThrow(NotFoundException::new);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
