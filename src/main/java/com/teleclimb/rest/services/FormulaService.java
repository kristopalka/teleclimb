package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.Formula;
import com.teleclimb.rest.entities.FormulaEntity;
import com.teleclimb.rest.repositories.FormulaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record FormulaService(ModelMapper mapper, FormulaRepository repo) {

    public List<Formula> getAll() {
        return repo.findAll()
                .stream()
                .map(c -> mapper.map(c, Formula.class))
                .collect(Collectors.toList());
    }

    public Formula get(Integer id) {
        FormulaEntity schema = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found formula by with: " + id));

        return mapper.map(schema, Formula.class);
    }
}
