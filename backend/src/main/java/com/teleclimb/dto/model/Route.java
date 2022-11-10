package com.teleclimb.dto.model;

import com.teleclimb.dto.enums.Discipline;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    private Integer id;

    private String name;

    private String description;

    private Discipline discipline;

    private LocalTime timeLimit;
}
