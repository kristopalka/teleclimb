package com.teleclimb.rest.dto;

import com.teleclimb.enums.Gender;
import lombok.Data;

@Data
public class Competition {
    private Integer id;

    private String name;

    private Integer categoryId;

    private Gender gender;

    private Integer formulaId;
}
