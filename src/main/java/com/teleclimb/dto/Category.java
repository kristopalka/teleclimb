package com.teleclimb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;

    private String name;

    private String shortName;

    private Integer fromAge;

    private Integer toAge;
}
