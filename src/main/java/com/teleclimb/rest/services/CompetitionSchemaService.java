package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.CompetitionSchemaDto;
import com.teleclimb.rest.entities.CompetitionSchema;
import com.teleclimb.rest.repositories.CompetitionSchemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record CompetitionSchemaService(ModelMapper mapper, CompetitionSchemaRepository repo) {

    public List<CompetitionSchemaDto> getAll() {
        return repo.findAll()
                .stream()
                .map(c -> mapper.map(c, CompetitionSchemaDto.class))
                .collect(Collectors.toList());
    }

    public CompetitionSchemaDto get(Long id) {
        CompetitionSchema schema = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found schema by with: " + id));

        return mapper.map(schema, CompetitionSchemaDto.class);
    }
}
