package com.teleclimb.rest.entities;

import com.teleclimb.rest.dto.CategoryDto;
import lombok.Data;
import org.modelmapper.ModelMapper;
import springfox.documentation.swagger2.mappers.ModelMapperImpl;

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

}
