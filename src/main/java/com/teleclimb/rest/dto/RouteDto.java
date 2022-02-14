package com.teleclimb.rest.dto;

import com.teleclimb.enums.CompetitionType;
import com.teleclimb.rest.entities.Route;
import lombok.Data;

@Data
public class RouteDto {
    private Long id;

    private CompetitionType competitionType;

    private String name;

    private String description;

    private Integer timeLimitSeconds;

}