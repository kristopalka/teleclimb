package com.teleclimb.rest.round;

import com.teleclimb.rest.competition.Competition;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private String name;

    private Integer maxContestants;

    public RoundDto toDto() {
        RoundDto dto = new RoundDto();

        dto.setId(this.getId());
        dto.setCompetition(this.getCompetition());
        dto.setName(this.getName());
        dto.setMaxContestants(this.getMaxContestants());

        return dto;
    }
}
