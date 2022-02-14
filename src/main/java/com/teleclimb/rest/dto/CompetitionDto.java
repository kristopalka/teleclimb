package com.teleclimb.rest.dto;

import com.teleclimb.enums.Discipline;
import com.teleclimb.enums.Gender;
import lombok.Data;

@Data
public class CompetitionDto {
    private Long id;

    private String name;

    private CategoryDto category;

    private Gender gender;

    private Discipline discipline;

}
