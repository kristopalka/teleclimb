package com.teleclimb.rest.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Round round;

    private String name;

}
