package com.teleclimb.rest.contestant;

import com.teleclimb.rest.competition.Competition;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContestantDto {
    private Long id;

    private Competition competition;

    private String name;

    private String lastName;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;

    public Contestant toEntity() {
        Contestant competition = new Contestant();

        competition.setId(this.getId());
        competition.setCompetition(this.getCompetition());
        competition.setName(this.getName());
        competition.setLastName(this.getLastName());
        competition.setStartNumber(this.getStartNumber());
        competition.setClubName(this.getClubName());
        competition.setBirthDate(this.getBirthDate());

        return competition;
    }
}
