package com.teleclimb.dto.model;

import com.teleclimb.dto.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionPost {
    private Integer id;

    private Integer categoryId;

    private Integer formulaId;

    private Gender gender;

    private String name;
}
