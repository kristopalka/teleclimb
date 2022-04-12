package com.teleclimb.entitie;

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

    private Integer topRoundNumber;  // the highest round number, participant reach

    // participant data
    private String name;

    private String lastName;

    private Integer rankingPosition;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;

}
