package com.teleclimb.rest.route;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepo;

    public List<RouteDto> getAll() {
        return routeRepo.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public RouteDto get(Long id) {
        return entityToDto(routeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found route with id: " + id)));
    }

    public void add(RouteDto dto) {
        dto.setId(null);
        newDtoValidation(dto);

        routeRepo.save(dtoToEntity(dto));
    }

    public void update(Long id, RouteDto newDto) {
        RouteDto dto = get(id);

        if(newDto.getName() != null) dto.setName(newDto.getName());
        if(newDto.getDescription() != null) dto.setDescription(newDto.getDescription());
        if(newDto.getTimeLimitSeconds() != null) dto.setName(newDto.getName());

        routeRepo.save(dtoToEntity(dto));
    }

    public void delete(Long id) {
        //todo remove all starts on this route
        routeRepo.deleteById(id);
    }



    private void newDtoValidation(RouteDto dto) {
        if (dto.getCompetitionType() == null) throw new BadRequestException("CompetitionType cannot be null");
    }

    private RouteDto entityToDto(Route route) {
        RouteDto dto = new RouteDto();

        dto.setId(route.getId());
        dto.setCompetitionType(route.getCompetitionType());
        dto.setName(route.getName());
        dto.setDescription(route.getDescription());
        dto.setTimeLimitSeconds(route.getTimeLimitSeconds());

        return dto;
    }

    private Route dtoToEntity(RouteDto dto) {
        Route route = new Route();

        route.setId(dto.getId());
        route.setCompetitionType(dto.getCompetitionType());
        route.setName(dto.getName());
        route.setDescription(dto.getDescription());
        route.setTimeLimitSeconds(dto.getTimeLimitSeconds());

        return route;
    }
}
