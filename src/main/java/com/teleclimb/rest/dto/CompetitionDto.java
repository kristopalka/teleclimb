package com.teleclimb.rest.dto;

import com.teleclimb.enums.Gender;
import lombok.Data;

@Data
public class CompetitionDto {
    private Integer id;

    private String name;

    private CategoryRawDto category;

    private Gender gender;

    private FormulaRawDto formula;
}
