package com.teleclimb.rest.dto;

import com.teleclimb.enums.Discipline;
import lombok.Data;

@Data
public class Formula {
    private Integer id;

    private String identifier;

    private Discipline discipline;

    private Integer numberOfRounds;

    private String name;

    private String description;

}
