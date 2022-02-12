package com.teleclimb.rest.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Start {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round roundId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route routeId;

    @ManyToOne
    @JoinColumn(name = "contestant_id")
    private Contestant contestantId;

    private String result; // if null, start have not done yet
}
//TODO bardzo ważna jest walidacja przy tworzeniu: czy contestant category i route competitiontype aplikują się do danego competition
