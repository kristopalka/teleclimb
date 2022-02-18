package com.teleclimb.rest.dto;

import com.teleclimb.enums.Gender;
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

    private Integer categoryId;

    private Integer formulaId;

    private Gender gender;

    private String name;
}
