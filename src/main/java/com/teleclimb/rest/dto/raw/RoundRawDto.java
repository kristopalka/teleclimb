package com.teleclimb.rest.dto.raw;

import com.teleclimb.enums.StartsGenerationMethod;
import lombok.Data;

@Data
public class RoundRawDto {
    private Long id;

    private Integer sequenceNumber;

    private Long competitionId;

    private String name;

    private Integer numberOfRoutes;

    private Integer maxParticipants;

    private String resultCalculatingFunction;

    private StartsGenerationMethod startsGenerationMethod;
}
