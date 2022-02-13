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

    public Category toEntity() {
        Category category = new Category();

        category.setId(this.getId());
        category.setShortName(this.getShortName());
        category.setName(this.getName());
        category.setFromAge(this.getFromAge());
        category.setToAge(this.getToAge());

        return category;
    }
}
