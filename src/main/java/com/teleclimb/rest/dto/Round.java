package com.teleclimb.rest.dto;

import com.teleclimb.enums.RoundState;
import com.teleclimb.enums.StartsGenerationMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Round {
    private Integer id;

    private Integer competitionId;

    private Integer sequenceNumber;

    private String name;

    private Integer numberOfRoutes;

    private Integer maxParticipants;

    private StartsGenerationMethod startsGenerationMethod;

    private String resultCalculatingFunction;

    private RoundState state;
}
