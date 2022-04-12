package com.teleclimb.entities;

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
    @JoinColumn(name = "referee_position_id")
    private RefereePositionEntity refereePosition;

    private Integer positionSequenceNumber;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private ParticipantEntity participant;

    private String result;

}