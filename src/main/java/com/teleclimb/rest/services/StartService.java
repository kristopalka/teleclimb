package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.StartDto;
import com.teleclimb.rest.entities.Start;
import com.teleclimb.rest.repositories.StartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record StartService(ModelMapper mapper, StartRepository startRepo) {

    public List<StartDto> getAll() {
        return startRepo.findAll()
                .stream()
                .map(s -> mapper.map(s, StartDto.class))
                .collect(Collectors.toList());
    }

    public StartDto get(Integer id) {
        Start start = startRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found start with id: " + id));

        return mapper.map(start, StartDto.class);
    }
}
