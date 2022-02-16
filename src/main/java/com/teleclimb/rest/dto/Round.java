package com.teleclimb.rest.dto;

import com.teleclimb.enums.StartsGenerationMethod;
import lombok.Data;

@Data
public class Round {
    private Integer id;

    private Integer competitionId;

    private Integer sequenceNumber;

    private String name;

    private Integer numberOfRoutes;

    private Integer maxParticipants;

    private StartsGenerationMethod startsGenerationMethod;

    private String resultCalculatingFunction;
}
