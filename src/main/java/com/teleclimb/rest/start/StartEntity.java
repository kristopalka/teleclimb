package com.teleclimb.rest.start;

import com.teleclimb.rest.contestant.ContestantEntity;
import com.teleclimb.rest.round.RoundEntity;
import com.teleclimb.rest.route.RouteEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "start")
public class StartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private RoundEntity roundId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteEntity routeId;

    @ManyToOne
    @JoinColumn(name = "contestant_id")
    private ContestantEntity contestantId;

    private String result; // if null, start have not done yet
}
//TODO bardzo ważna jest walidacja przy tworzeniu: czy competition i route mają ten sam typ
