package com.teleclimb.rest.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sequenceNumber;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private String name;

    private Integer maxParticipants;

}
