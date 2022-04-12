package com.teleclimb.entitie;

import com.teleclimb.dto.enums.Discipline;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "route")
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Discipline discipline;

    private Integer timeLimitSeconds;
}
