package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.RoundDto;
import com.teleclimb.rest.entities.Round;
import com.teleclimb.rest.repositories.RoundRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record RoundService(ModelMapper mapper, RoundRepository roundRepo) {

    public List<RoundDto> getAll() {
        return roundRepo.findAll()
                .stream()
                .map(r -> mapper.map(r,RoundDto.class))
                .collect(Collectors.toList());
    }

    public RoundDto get(Long id) {
        Round round = roundRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found round with id: " + id));

        return mapper.map(round, RoundDto.class);
    }
}
