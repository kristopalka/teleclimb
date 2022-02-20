package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.Start;
import com.teleclimb.rest.entities.StartEntity;
import com.teleclimb.rest.repositories.StartRepository;
import com.teleclimb.rest.responses.error.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record StartService(ModelMapper mapper, StartRepository startRepo) {

    // --------------------------------- GET ---------------------------------

    public Start get(Integer id) {
        StartEntity startEntity = startRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found start with id: " + id));

        return toDto(startEntity);
    }

    public List<Start> getAll() {
        return startRepo.findAll().stream().map(this::toDto).toList();
    }

    public List<Start> getAllByRefereePositionId(Integer positionId) {
        return startRepo.findByRefereePositionId(positionId).stream().map(this::toDto).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public Start add(Start start) {
        validateStart(start);

        return toDto(startRepo.save(toEntity(start)));
    }

    private void validateStart(Start start) {
        //todo validate start
    }

    public void addAll(List<Start> starts) {
        starts.forEach(this::add);
    }


    // --------------------------------- MAPPING ---------------------------------

    private Start toDto(StartEntity entity) {
        return mapper.map(entity, Start.class);
    }

    private StartEntity toEntity(Start dto) {
        return mapper.map(dto, StartEntity.class);
    }

}
