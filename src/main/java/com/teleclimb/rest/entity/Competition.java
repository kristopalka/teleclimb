package com.teleclimb.rest.entity;

import com.sun.istack.NotNull;
import com.teleclimb.enums.CompetitionType;
import com.teleclimb.enums.Gender;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;


    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CompetitionType competitionType;

}
