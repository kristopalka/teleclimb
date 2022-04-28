package com.teleclimb.entitie;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "referee_position")
public class RefereePositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer hash;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteEntity route;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private RoundEntity round;
}
