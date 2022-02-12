package com.teleclimb.rest.contestant;

import com.teleclimb.rest.competition.Competition;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Contestant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competitionId;

    private String name;

    private String lastName;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;
}
