package com.teleclimb.rest.entities;

import com.teleclimb.enums.CompetitionType;
import com.teleclimb.rest.dto.RouteDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private CompetitionType competitionType;

    private Integer timeLimitSeconds;

}
