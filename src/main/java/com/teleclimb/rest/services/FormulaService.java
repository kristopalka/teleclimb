package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.FormulaRawDto;
import com.teleclimb.rest.entities.Formula;
import com.teleclimb.rest.repositories.FormulaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record FormulaService(ModelMapper mapper, FormulaRepository repo) {

    public List<FormulaRawDto> getAll() {
        return repo.findAll()
                .stream()
                .map(c -> mapper.map(c, FormulaRawDto.class))
                .collect(Collectors.toList());
    }

    public FormulaRawDto get(Integer id) {
        Formula schema = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found formula by with: " + id));

        return mapper.map(schema, FormulaRawDto.class);
    }
}
