package com.teleclimb.rest.dto;

import com.teleclimb.enums.Discipline;
import lombok.Data;

@Data
public class FormulaDto {
    private Long id;

    private String identifier;

    private Discipline discipline;

    private String name;

    private String description;

}
