package com.teleclimb.service;

import com.teleclimb.controller.responses.error.exception.NotFoundException;
import com.teleclimb.dto.model.Formula;
import com.teleclimb.entitie.FormulaEntity;
import com.teleclimb.repository.FormulaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record FormulaService(ModelMapper mapper, FormulaRepository formulaRepo) {

    // --------------------------------- GET ---------------------------------

    public Formula get(Integer id) {
        FormulaEntity formula = formulaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found formula with: " + id));

        return mapper.map(formula, Formula.class);
    }

    public List<Formula> getAll() {
        return formulaRepo.findAll().stream().map(entity -> mapper.map(entity, Formula.class)).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public Formula add(Formula formula) {
        validateFormula(formula);
        return mapper.map(formulaRepo.save(mapper.map(formula, FormulaEntity.class)), Formula.class);
    }

    private void validateFormula(Formula formula) {
        //todo formula validation
    }

}
