package com.teleclimb.rest.dto;

import com.teleclimb.enums.StartsGenerationMethod;
import com.teleclimb.rest.entities.Competition;
import lombok.Data;

@Data
public class RoundDto {
    private Integer id;

    private Integer sequenceNumber;

    private Competition competition;

    private String name;

    private Integer numberOfRoutes;

    private Integer maxParticipants;

    private String resultCalculatingFunction;

    private StartsGenerationMethod startsGenerationMethod;
}
