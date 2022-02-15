package com.teleclimb.rest.dto;

import com.teleclimb.enums.Gender;
import com.teleclimb.rest.entities.Formula;
import lombok.Data;

@Data
public class CompetitionDto {
    private Long id;

    private String name;

    private CategoryDto category;

    private Gender gender;

    private Formula formula;
}
