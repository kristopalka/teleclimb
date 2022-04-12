package com.teleclimb.service;

import com.teleclimb.controller.responses.error.exception.BadRequestException;
import com.teleclimb.dto.model.RefereePosition;
import com.teleclimb.entitie.RefereePositionEntity;
import com.teleclimb.repository.RefereePositionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RefereePositionService(ModelMapper mapper, RefereePositionRepository positionRepo) {

    // --------------------------------- GET ---------------------------------

    public RefereePosition getByRoundIdAndRouteId(Integer roundId, Integer routeId) {
        return mapper.map(positionRepo.findByRoundIdAndRouteId(roundId, routeId).get(0), RefereePosition.class);
    }

    public List<RefereePosition> getAllByRoundId(Integer roundId) {
        return positionRepo.findByRoundId(roundId).stream()
                .map(entity -> mapper.map(entity, RefereePosition.class))
                .toList();
    }

    public List<RefereePosition> getAllByRouteId(Integer routeId) {
        return positionRepo.findByRouteId(routeId).stream()
                .map(entity -> mapper.map(entity, RefereePosition.class))
                .toList();
    }


    // --------------------------------- ADD ---------------------------------

    public void addPosition(Integer roundId, Integer routeId) {
        if (doesPositionExist(roundId, routeId))
            throw new BadRequestException("There is existing position for route id: " + roundId + " and round id: " + roundId);

        RefereePosition position = new RefereePosition();
        position.setRoundId(roundId);
        position.setRouteId(routeId);
        positionRepo.save(mapper.map(position, RefereePositionEntity.class));
    }

    private boolean doesPositionExist(Integer roundId, Integer routeId) {
        return positionRepo.findByRoundIdAndRouteId(roundId, routeId).size() != 0;
    }


    // --------------------------------- DELETE ---------------------------------

    public void removePosition(Integer roundId, Integer routeId) {
        if (doesPositionExist(roundId, routeId))
            throw new BadRequestException("There is no position to remove, considering route id: " + roundId + " and round id: " + roundId);

        List<RefereePositionEntity> positions = positionRepo.findByRoundIdAndRouteId(roundId, routeId);

        positionRepo.deleteAll(positions);
    }
}
