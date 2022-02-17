package com.teleclimb.rest.dto;

import com.teleclimb.enums.Discipline;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private Integer id;

    private String name;

    private String description;

    private Discipline discipline;

    private Integer timeLimitSeconds;
}
