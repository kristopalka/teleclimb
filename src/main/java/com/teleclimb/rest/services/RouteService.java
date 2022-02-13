package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.RouteDto;
import com.teleclimb.rest.entities.Route;
import com.teleclimb.rest.repositories.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record RouteService(RouteRepository routeRepo) {

    public List<RouteDto> getAll() {
        return routeRepo.findAll().stream().map(Route::toDto).collect(Collectors.toList());
    }

    public RouteDto get(Long id) {
        return routeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found route with id: " + id)).toDto();
    }

    public void add(RouteDto dto) {
        dto.setId(null);
        newDtoValidation(dto);

        routeRepo.save(dto.toEntity());
    }

    public void update(Long id, RouteDto newDto) {
        RouteDto dto = get(id);

        if (newDto.getName() != null) dto.setName(newDto.getName());
        if (newDto.getDescription() != null) dto.setDescription(newDto.getDescription());
        if (newDto.getTimeLimitSeconds() != null) dto.setName(newDto.getName());

        routeRepo.save(dto.toEntity());
    }

    public void delete(Long id) {
        //todo remove all starts on this route
        routeRepo.deleteById(id);
    }


    private void newDtoValidation(RouteDto dto) {
        if (dto.getCompetitionType() == null) throw new BadRequestException("CompetitionType cannot be null");
    }
}
