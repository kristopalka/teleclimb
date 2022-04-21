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
public class Competition {
    private Integer id;

    private Category category;

    private Formula formula;

    private Discipline discipline;

    private Gender gender;

    private String name;
}
