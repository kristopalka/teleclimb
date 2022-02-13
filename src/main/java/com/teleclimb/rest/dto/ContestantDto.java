package com.teleclimb.rest.dto;

import com.teleclimb.rest.entities.Competition;
import com.teleclimb.rest.entities.Contestant;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContestantDto {
    private Long id;

    private CompetitionDto competition;

    private String name;

    private String lastName;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;


    public Contestant toEntity() {
        Contestant competition = new Contestant();

        competition.setId(this.getId());
        competition.setCompetition(this.getCompetition().toEntity());
        competition.setName(this.getName());
        competition.setLastName(this.getLastName());
        competition.setStartNumber(this.getStartNumber());
        competition.setClubName(this.getClubName());
        competition.setBirthDate(this.getBirthDate());

        return competition;
    }
}
