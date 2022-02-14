package com.teleclimb.rest.dto;

import com.teleclimb.rest.entities.Round;
import lombok.Data;

@Data
public class RoundDto {
    private Long id;

    private CompetitionDto competition;

    private String name;

    private Integer maxParticipants;

}
