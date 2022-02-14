package com.teleclimb.rest.dto;

import com.teleclimb.enums.CompetitionType;
import com.teleclimb.rest.entities.CompetitionSchema;
import lombok.Data;

@Data
public class CompetitionSchemaDto {
    private Long id;

    private String identifier;

    private CompetitionType competitionType;

    private String name;

    private String description;

}
