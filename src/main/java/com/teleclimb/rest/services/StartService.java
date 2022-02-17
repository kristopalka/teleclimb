package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.Start;
import com.teleclimb.rest.entities.StartEntity;
import com.teleclimb.rest.repositories.StartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record StartService(ModelMapper mapper, StartRepository startRepo) {

    public List<Start> getAll() {
        return startRepo.findAll()
                .stream()
                .map(s -> mapper.map(s, Start.class))
                .collect(Collectors.toList());
    }

    public Start get(Integer id) {
        StartEntity startEntity = startRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found start with id: " + id));

        return mapper.map(startEntity, Start.class);
    }

    public void addAll(List<Start> starts) {
        starts.forEach(this::newStartValidation);
        starts.forEach(r -> startRepo.save(toEntity(r)));
    }

    private void newStartValidation(Start start) {
        //todo start validation
    }

    private Start toDto(StartEntity entity) {
        return mapper.map(entity, Start.class);
    }

    private StartEntity toEntity(Start dto) {
        return mapper.map(dto, StartEntity.class);
    }
}
