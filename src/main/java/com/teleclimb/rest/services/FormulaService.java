package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.Formula;
import com.teleclimb.rest.entities.FormulaEntity;
import com.teleclimb.rest.repositories.FormulaRepository;
import com.teleclimb.rest.responses.error.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record FormulaService(ModelMapper mapper, FormulaRepository formulaRepo) {

    public List<Formula> getAll() {
        return formulaRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Formula get(Integer id) {
        FormulaEntity formula = formulaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found formula by with: " + id));

        return toDto(formula);
    }

    public Formula add(Formula Formula) {
        Formula.setId(null);

        return toDto(formulaRepo.save(toEntity(Formula)));
    }


    private Formula toDto(FormulaEntity entity) {
        return mapper.map(entity, Formula.class);
    }

    private FormulaEntity toEntity(Formula dto) {
        return mapper.map(dto, FormulaEntity.class);
    }
}
