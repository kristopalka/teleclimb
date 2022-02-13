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

    public Route toEntity() {
        Route route = new Route();

        route.setId(this.getId());
        route.setCompetitionType(this.getCompetitionType());
        route.setName(this.getName());
        route.setDescription(this.getDescription());
        route.setTimeLimitSeconds(this.getTimeLimitSeconds());

        return route;
    }
}
