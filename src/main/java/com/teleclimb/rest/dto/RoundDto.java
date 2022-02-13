package com.teleclimb.rest.dto;

import com.teleclimb.rest.entities.Competition;
import com.teleclimb.rest.entities.Round;
import lombok.Data;

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
