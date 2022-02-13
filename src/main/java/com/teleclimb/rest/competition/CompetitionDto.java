package com.teleclimb.rest.competition;

import com.teleclimb.enums.CompetitionType;
import com.teleclimb.enums.Gender;
import com.teleclimb.rest.category.Category;
import lombok.Data;

@Data
public class CompetitionDto {
    private Long id;

    private String name;

    private Category category;

    private Gender gender;

    private CompetitionType competitionType;
}
