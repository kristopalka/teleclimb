package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.RoundDto;
import com.teleclimb.rest.entities.Round;
import com.teleclimb.rest.repositories.RoundRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record RoundService(RoundRepository roundRepo) {

    public List<RoundDto> getAll() {
        return roundRepo.findAll().stream().map(Round::toDto).collect(Collectors.toList());
    }

    public RoundDto get(Long id) {
        return roundRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found round with id: " + id)).toDto();
    }
}
