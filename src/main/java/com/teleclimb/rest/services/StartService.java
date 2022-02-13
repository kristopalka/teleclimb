package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.StartDto;
import com.teleclimb.rest.entities.Start;
import com.teleclimb.rest.repositories.StartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record StartService(StartRepository startRepo) {

    public List<StartDto> getAll() {
        return startRepo.findAll().stream().map(Start::toDto).collect(Collectors.toList());
    }

    public StartDto get(Long id) {
        return startRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found start with id: " + id)).toDto();
    }
}
