package com.teleclimb.entitie;

import com.teleclimb.dto.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
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
