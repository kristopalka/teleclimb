package com.teleclimb.dto.model;

import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.dto.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionWithAll {
    private Integer id;

    private Integer categoryId;

    private String categoryName;

    private Integer formulaId;

    private Discipline discipline;

    private Integer numberOfRounds;

    private Gender gender;

    private String name;
}
