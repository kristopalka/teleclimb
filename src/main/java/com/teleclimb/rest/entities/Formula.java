package com.teleclimb.rest.entities;

import com.teleclimb.enums.Discipline;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    private Discipline discipline;

    private String name;

    private String description;

}
