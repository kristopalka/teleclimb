package com.teleclimb.dto.model;

import com.teleclimb.dto.enums.ResultCalculatingFunction;
import com.teleclimb.dto.enums.RoundState;
import com.teleclimb.dto.enums.StartsGenerationMethod;
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

    private ResultCalculatingFunction resultCalculatingFunction;

    private RoundState state;
}
