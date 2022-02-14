package com.teleclimb.rest.dto;

import com.teleclimb.rest.entities.Category;
import lombok.Data;

@Data
public class CategoryDto {
    private Long id;

    private String name;

    private String shortName;

    private Integer fromAge;

    private Integer toAge;
}
