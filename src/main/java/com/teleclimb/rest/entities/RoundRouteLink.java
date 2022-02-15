package com.teleclimb.rest.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RoundRouteLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;
}
