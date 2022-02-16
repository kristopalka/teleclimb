package com.teleclimb.rest.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String shortName;

    private Integer fromAge;

    private Integer toAge;

}
