package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.RouteRawDto;
import com.teleclimb.rest.entities.Route;
import com.teleclimb.rest.repositories.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record RouteService(ModelMapper mapper, RouteRepository routeRepo) {

    public List<RouteRawDto> getAll() {
        return routeRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public RouteRawDto get(Integer id) {
        Route route = routeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found route with id: " + id));
        return toDto(route);
    }

    public RouteRawDto add(RouteRawDto dto) {
        dto.setId(null);
        newDtoValidation(dto);

        Route route = routeRepo.save(toEntity(dto));
        return toDto(route);
    }

    public RouteRawDto update(Integer id, RouteRawDto newDto) {
        RouteRawDto dto = get(id);

        if (newDto.getName() != null) dto.setName(newDto.getName());
        if (newDto.getDescription() != null) dto.setDescription(newDto.getDescription());
        if (newDto.getTimeLimitSeconds() != null) dto.setName(newDto.getName());

        Route route = routeRepo.save(toEntity(dto));
        return toDto(route);
    }

    public void delete(Integer id) {
        //todo remove all starts on this route
        routeRepo.deleteById(id);
    }


    private void newDtoValidation(RouteRawDto dto) {
        if (dto.getDiscipline() == null) throw new BadRequestException("CompetitionType cannot be null");
    }

    private RouteRawDto toDto(Route entity) {
        return mapper.map(entity, RouteRawDto.class);
    }

    private Route toEntity(RouteRawDto dto) {
        return mapper.map(dto, Route.class);
    }
}
