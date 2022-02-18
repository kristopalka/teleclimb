package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.Formula;
import com.teleclimb.rest.entities.FormulaEntity;
import com.teleclimb.rest.repositories.FormulaRepository;
import com.teleclimb.rest.responses.error.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record FormulaService(ModelMapper mapper, FormulaRepository formulaRepo) {

    // --------------------------------- GET ---------------------------------

    public Formula get(Integer id) {
        FormulaEntity formula = formulaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found formula by with: " + id));

        return toDto(formula);
    }

    public List<Formula> getAll() {
        return formulaRepo.findAll().stream().map(this::toDto).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public Formula add(Formula formula) {
        validateFormula(formula);
        return toDto(formulaRepo.save(toEntity(formula)));
    }

    private void validateFormula(Formula formula) {
        //todo formula validation
    }


    // --------------------------------- MAPPING ---------------------------------

    private Formula toDto(FormulaEntity entity) {
        return mapper.map(entity, Formula.class);
    }

    private FormulaEntity toEntity(Formula dto) {
        return mapper.map(dto, FormulaEntity.class);
    }
}
