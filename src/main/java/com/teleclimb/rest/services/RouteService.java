package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.RouteDto;
import com.teleclimb.rest.entities.Route;
import com.teleclimb.rest.repositories.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record RouteService(ModelMapper mapper, RouteRepository routeRepo) {

    public List<RouteDto> getAll() {
        return routeRepo.findAll()
                .stream()
                .map(r -> mapper.map(r, RouteDto.class))
                .collect(Collectors.toList());
    }

    public RouteDto get(Long id) {
        Route route = routeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found route with id: " + id));

        return mapper.map(route, RouteDto.class);
    }

    public void add(RouteDto dto) {
        dto.setId(null);
        newDtoValidation(dto);

        routeRepo.save(mapper.map(dto, Route.class));
    }

    public void update(Long id, RouteDto newDto) {
        RouteDto dto = get(id);

        if (newDto.getName() != null) dto.setName(newDto.getName());
        if (newDto.getDescription() != null) dto.setDescription(newDto.getDescription());
        if (newDto.getTimeLimitSeconds() != null) dto.setName(newDto.getName());

        routeRepo.save(mapper.map(dto, Route.class));
    }

    public void delete(Long id) {
        //todo remove all starts on this route
        routeRepo.deleteById(id);
    }


    private void newDtoValidation(RouteDto dto) {
        if (dto.getDiscipline() == null) throw new BadRequestException("CompetitionType cannot be null");
    }
}
