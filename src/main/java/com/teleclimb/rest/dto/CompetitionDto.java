package com.teleclimb.rest.dto;

import com.teleclimb.enums.CompetitionType;
import com.teleclimb.enums.Gender;
import com.teleclimb.rest.entities.Category;
import com.teleclimb.rest.entities.Competition;
import lombok.Data;

@Data
public class CompetitionDto {
    private Long id;

    private String name;

    private CategoryDto category;

    private Gender gender;

    private CompetitionType competitionType;

}
