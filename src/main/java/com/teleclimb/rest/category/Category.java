package com.teleclimb.rest.category;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String shortName;

    private Integer fromAge;

    private Integer toAge;

    public CategoryDto toDto() {
        CategoryDto dto = new CategoryDto();

        dto.setId(this.getId());
        dto.setShortName(this.getShortName());
        dto.setName(this.getName());
        dto.setFromAge(this.getFromAge());
        dto.setToAge(this.getToAge());

        return dto;
    }
}
