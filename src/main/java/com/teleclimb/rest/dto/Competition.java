package com.teleclimb.rest.dto;

import com.teleclimb.enums.Gender;
import lombok.Data;

@Data
public class Competition {
    private Integer id;

    private Integer categoryId;

    private Integer formulaId;

    private Gender gender;

    private String name;
}
