package com.teleclimb.rest.round;

import com.teleclimb.rest.competition.Competition;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
public class RoundDto {
    private Long id;

    private Competition competition;

    private String name;

    private Integer maxContestants;

    public Round toEntity() {
        Round round = new Round();

        round.setId(this.getId());
        round.setCompetition(this.getCompetition());
        round.setName(this.getName());
        round.setMaxContestants(this.getMaxContestants());

        return round;
    }
}
