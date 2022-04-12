package com.teleclimb.services;

import com.teleclimb.controllers.responses.error.exception.BadRequestException;
import com.teleclimb.controllers.responses.error.exception.NotFoundException;
import com.teleclimb.dto.RefereePosition;
import com.teleclimb.dto.Route;
import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.entities.RouteEntity;
import com.teleclimb.repositories.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RouteService(ModelMapper mapper, RouteRepository routeRepo, RefereePositionService positionService) {

    // --------------------------------- GET ---------------------------------

    public Route get(Integer id) {
        RouteEntity routeEntity = routeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found route with id: " + id));

        return mapper.map(routeEntity, Route.class);
    }

    public List<Route> getAll() {
        return routeRepo.findAll().stream().map(entity -> mapper.map(entity, Route.class)).toList();
    }

    public List<Route> getAllByDiscipline(Discipline discipline) {
        return routeRepo.findByDiscipline(discipline).stream().map(entity -> mapper.map(entity, Route.class)).toList();
    }

    public List<Route> getAllByRoundId(Integer roundId) {
        List<RefereePosition> positions = positionService.getAllByRoundId(roundId);
        return positions.stream().map(RefereePosition::getRouteId).map(this::get).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public Route add(Route dto) {
        validateRoute(dto);
        return mapper.map(routeRepo.save(mapper.map(dto, RouteEntity.class)), Route.class);
    }

    private void validateRoute(Route dto) {
        if (dto.getDiscipline() == null) throw new BadRequestException("CompetitionType cannot be null");
    }


    // --------------------------------- UPDATE ---------------------------------

    public Route update(Integer id, Route newRoute) {
        Route route = get(id);

        if (newRoute.getName() != null) route.setName(newRoute.getName());
        if (newRoute.getDescription() != null) route.setDescription(newRoute.getDescription());
        if (newRoute.getTimeLimitSeconds() != null) route.setName(newRoute.getName());

        return mapper.map(routeRepo.save(mapper.map(route, RouteEntity.class)), Route.class);
    }


    // --------------------------------- DELETE ---------------------------------

    public void delete(Integer id) {
        //todo remove all starts on this route
        routeRepo.deleteById(id);
    }
}
