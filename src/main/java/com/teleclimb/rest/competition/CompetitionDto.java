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

    public Competition toEntity() {
        Competition competition = new Competition();

        competition.setId(this.getId());
        competition.setName(this.getName());
        competition.setGender(this.getGender());
        competition.setCategory(this.getCategory());
        competition.setCompetitionType(this.getCompetitionType());

        return competition;
    }
}
