package com.teleclimb.rest.entities;

import com.teleclimb.enums.CompetitionType;
import com.teleclimb.rest.dto.CompetitionSchemaDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CompetitionSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    private CompetitionType competitionType;

    private String name;

    private String description;

}
