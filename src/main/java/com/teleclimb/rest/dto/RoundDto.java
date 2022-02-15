package com.teleclimb.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoundDto {
    private Long id;

    private Long sequenceNumber;

    private String resultCalculatingFunction;

    private List<RouteDto> routes;

    private Integer numberOfRoutes;

    private CompetitionDto competition;

    private String name;

    private Integer maxParticipants;

}
