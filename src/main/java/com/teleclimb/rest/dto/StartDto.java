package com.teleclimb.rest.dto;

import lombok.Data;

@Data
public class StartDto {
    private Long id;

    private RoundDto round;

    private RouteDto route;

    private Integer routeSequenceNumber;

    private ParticipantDto participant;

    private String result;

}
