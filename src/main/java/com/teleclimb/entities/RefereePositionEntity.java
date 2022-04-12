package com.teleclimb.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "referee_position")
public class RefereePositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteEntity route;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private RoundEntity round;
}
