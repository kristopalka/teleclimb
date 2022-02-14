package com.teleclimb.rest.dto.raw;

import com.teleclimb.rest.dto.CompetitionDto;
import lombok.Data;

@Data
public class RoundRawDto {
    private Long id;

    private Long competitionId;

    private String name;

    private Integer maxParticipants;

}
