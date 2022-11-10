package com.teleclimb.dto.model;

import com.teleclimb.dto.enums.Discipline;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Formula {
    private Integer id;

    private String name;

    private String description;

    private Discipline discipline;

    private Integer numberOfRounds;

    private String jsonConfiguration;
}
