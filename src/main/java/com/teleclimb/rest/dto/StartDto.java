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
    private Integer id;

    private RoundDto round;

    private RouteRawDto route;

    private Integer routeSequenceNumber;

    private ParticipantDto participant;

    private String result;

}
