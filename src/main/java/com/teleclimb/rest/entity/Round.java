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
    @JoinColumn(name = "id")
    private Competition competition;

    private String name;

    private Integer maxContestants;

}
