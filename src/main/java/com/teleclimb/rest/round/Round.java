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

}
