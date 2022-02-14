package com.teleclimb.rest.entities;

import com.teleclimb.rest.dto.ParticipantDto;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private String name;

    private String lastName;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;

}
