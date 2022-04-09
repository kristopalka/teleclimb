package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.Start;
import com.teleclimb.rest.entities.StartEntity;
import com.teleclimb.rest.repositories.StartRepository;
import com.teleclimb.rest.responses.error.exception.BadRequestException;
import com.teleclimb.rest.responses.error.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record StartService(ModelMapper mapper, StartRepository startRepo, RefereePositionService positionService) {

    // --------------------------------- GET ---------------------------------

    public Start get(Integer id) {
        StartEntity startEntity = startRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found start with id: " + id));

        return mapper.map(startEntity, Start.class);
    }

    public List<Start> getAll() {
        return startRepo.findAll().stream().map(entity -> mapper.map(entity, Start.class)).toList();
    }

    public List<Start> getAllByRefereePositionId(Integer positionId) {
        return startRepo.findByRefereePositionId(positionId).stream().map(entity -> mapper.map(entity, Start.class)).toList();
    }

    public List<Start> getByRoundIdAndRouteId(Integer roundId, Integer routeId) {
        try {
            Integer positionId = positionService.getByRoundIdAndRouteId(roundId, routeId).getId();
            return startRepo.findByRefereePositionId(positionId).stream().map(entity -> mapper.map(entity, Start.class)).toList();
        } catch (RuntimeException e) {
            throw new BadRequestException("Route " + routeId + " is not added to round " + roundId);
        }
    }


    // --------------------------------- ADD ---------------------------------

    public Start add(Start start) {
        validateStart(start);

        return mapper.map(startRepo.save(mapper.map(start, StartEntity.class)), Start.class);
    }

    private void validateStart(Start start) {
        //todo validate start
    }

    public void addAll(List<Start> starts) {
        starts.forEach(this::add);
    }


    // --------------------------------- UPDATE ---------------------------------

    public void updateResult(Integer id, String result) {
        //todo
    }
}
