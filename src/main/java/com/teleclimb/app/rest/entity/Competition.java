package com.teleclimb.app.rest.entity;

import com.teleclimb.app.enums.CompetitionType;
import com.teleclimb.app.enums.Gender;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private CompetitionType competitionType;
}
