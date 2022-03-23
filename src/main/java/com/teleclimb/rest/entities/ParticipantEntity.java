package com.teleclimb.rest.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "participant")
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private CompetitionEntity competition;

    private Integer roundSequenceNumber;  // point the highest round, participant reach

    private String name;

    private String lastName;

    private Integer rankingPosition;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;

}
