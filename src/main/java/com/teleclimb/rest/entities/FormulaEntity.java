package com.teleclimb.rest.entities;

import com.teleclimb.enums.Discipline;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "formula")
public class FormulaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identifier;

    @Enumerated(EnumType.STRING)
    private Discipline discipline;

    private Integer numberOfRounds;

    private String name;

    private String description;
}
