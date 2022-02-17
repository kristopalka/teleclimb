package com.teleclimb.rest.services;

import com.teleclimb.enums.Discipline;
import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.Route;
import com.teleclimb.rest.entities.RouteEntity;
import com.teleclimb.rest.repositories.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record RouteService(ModelMapper mapper, RouteRepository routeRepo) {

    public List<Route> getAll() {
        return routeRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Route> getAllByDiscipline(String disciplineName) {
        Discipline discipline = mapper.map(disciplineName, Discipline.class);
        return routeRepo.findByDiscipline(discipline)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Route> getAllByRoundId(Integer roundId) {
        return routeRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Route get(Integer id) {
        RouteEntity routeEntity = routeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found route with id: " + id));
        return toDto(routeEntity);
    }

    public Route add(Route dto) {
        dto.setId(null);
        newDtoValidation(dto);

        RouteEntity routeEntity = routeRepo.save(toEntity(dto));
        return toDto(routeEntity);
    }

    public Route update(Integer id, Route newRoute) {
        Route route = get(id);

        if (newRoute.getName() != null) route.setName(newRoute.getName());
        if (newRoute.getDescription() != null) route.setDescription(newRoute.getDescription());
        if (newRoute.getTimeLimitSeconds() != null) route.setName(newRoute.getName());

        RouteEntity routeEntity = routeRepo.save(toEntity(route));
        return toDto(routeEntity);
    }

    public void delete(Integer id) {
        //todo remove all starts on this route
        routeRepo.deleteById(id);
    }


    private void newDtoValidation(Route dto) {
        if (dto.getDiscipline() == null) throw new BadRequestException("CompetitionType cannot be null");
    }

    private Route toDto(RouteEntity entity) {
        return mapper.map(entity, Route.class);
    }

    private RouteEntity toEntity(Route dto) {
        return mapper.map(dto, RouteEntity.class);
    }

}
