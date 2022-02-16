package com.teleclimb.rest.dto;

import com.teleclimb.enums.StartsGenerationMethod;
import lombok.Data;

@Data
public class RoundRawDto {
    private Integer id;

    private Integer sequenceNumber;

    private Integer competitionId;

    private String name;

    private Integer numberOfRoutes;

    private Integer maxParticipants;

    private String resultCalculatingFunction;

    private StartsGenerationMethod startsGenerationMethod;
}
