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
