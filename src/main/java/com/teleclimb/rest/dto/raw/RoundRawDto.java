package com.teleclimb.rest.dto.raw;

import lombok.Data;

@Data
public class RoundRawDto {
    private Long id;

    private Long competitionId;

    private String name;

    private Integer maxParticipants;

}
