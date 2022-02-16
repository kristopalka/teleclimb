package com.teleclimb.rest.dto;

import com.teleclimb.enums.Discipline;
import lombok.Data;

@Data
public class Route {
    private Integer id;

    private String name;

    private String description;

    private Discipline discipline;

    private Integer timeLimitSeconds;
}
