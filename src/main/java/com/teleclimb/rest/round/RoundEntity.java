package com.teleclimb.rest.round;

import com.teleclimb.rest.competition.CompetitionEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "round")
public class RoundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private CompetitionEntity competitionId;

    private String name;

    private Integer maxContestants;

}
