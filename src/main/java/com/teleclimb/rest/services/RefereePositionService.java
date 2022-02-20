package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.RefereePosition;
import com.teleclimb.rest.entities.RefereePositionEntity;
import com.teleclimb.rest.repositories.RefereePositionRepository;
import com.teleclimb.rest.responses.error.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RefereePositionService(ModelMapper mapper, RefereePositionRepository positionRepo) {

    // --------------------------------- GET ---------------------------------

    public List<RefereePosition> getAllPositionsByRoundId(Integer roundId) {
        return positionRepo.findByRoundId(roundId).stream().map(this::toDto).toList();
    }

    public List<RefereePosition> getAllPositionsByRouteId(Integer routeId) {
        return positionRepo.findByRouteId(routeId).stream().map(this::toDto).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public void addPosition(Integer roundId, Integer routeId) {
        if (doesPositionExist(roundId, routeId))
            throw new BadRequestException("There is existing position between route id: " + roundId + " and round id: " + roundId);

        RefereePosition position = new RefereePosition();
        position.setRoundId(roundId);
        position.setRouteId(routeId);
        positionRepo.save(toEntity(position));
    }

    private boolean doesPositionExist(Integer roundId, Integer routeId) {
        return positionRepo.findByRoundIdAndRouteId(roundId, routeId).size() != 0;
    }


    // --------------------------------- DELETE ---------------------------------

    public void removePosition(Integer roundId, Integer routeId) {
        if (doesPositionExist(roundId, routeId))
            throw new BadRequestException("There is no position to remove, between routeEntity id: " + roundId + " and roundEntity id: " + roundId);

        List<RefereePositionEntity> positions = positionRepo.findByRoundIdAndRouteId(roundId, routeId);

        positionRepo.deleteAll(positions);
    }


    // --------------------------------- MAPPING ---------------------------------

    private RefereePosition toDto(RefereePositionEntity entity) {
        return mapper.map(entity, RefereePosition.class);
    }

    private RefereePositionEntity toEntity(RefereePosition dto) {
        return mapper.map(dto, RefereePositionEntity.class);
    }
}
