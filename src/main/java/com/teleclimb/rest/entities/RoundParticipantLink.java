package com.teleclimb.rest.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RoundParticipantLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;
}
