package com.teleclimb.rest.route;

import com.teleclimb.enums.CompetitionType;
import lombok.Data;

@Data
public class RouteDto {
    private Long id;

    private CompetitionType competitionType;

    private String name;

    private String description;

    private Integer timeLimitSeconds;
}
