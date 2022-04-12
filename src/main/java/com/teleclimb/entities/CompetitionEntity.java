package com.teleclimb.entities;

import com.teleclimb.dto.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "competition")
public class CompetitionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "formula_id")
    private FormulaEntity formula;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String name;
}
