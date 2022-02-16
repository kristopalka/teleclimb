package com.teleclimb.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartDto {
    private Long id;

    private RoundDto round;

    private RouteDto route;

    private Integer routeSequenceNumber;

    private ParticipantDto participant;

    private String result;

}
