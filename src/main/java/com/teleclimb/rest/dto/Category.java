package com.teleclimb.rest.dto;

import lombok.Data;

@Data
public class Category {
    private Integer id;

    private String name;

    private String shortName;

    private Integer fromAge;

    private Integer toAge;
}
