package com.teleclimb.rest.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "start")
public class StartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private RoundEntity round;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteEntity route;

    private Integer routeSequenceNumber;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private ParticipantEntity participant;

    private String result;

}