package com.teleclimb.rest.entities;

import com.teleclimb.enums.CompetitionType;
import com.teleclimb.enums.Gender;
import com.teleclimb.rest.dto.CompetitionDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private CompetitionType competitionType;

}
