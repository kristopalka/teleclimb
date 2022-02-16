package com.teleclimb.rest.dto;

import com.teleclimb.enums.Discipline;
import lombok.Data;

@Data
public class RouteRawDto {
    private Integer id;

    private Discipline discipline;

    private String name;

    private String description;

    private Integer timeLimitSeconds;

}
