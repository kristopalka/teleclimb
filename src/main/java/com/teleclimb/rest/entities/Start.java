package com.teleclimb.rest.entities;

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
    private Round round;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private Integer routeSequenceNumber;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    private String result;

}