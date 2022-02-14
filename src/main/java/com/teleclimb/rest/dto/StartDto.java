package com.teleclimb.rest.dto;

import com.teleclimb.rest.entities.Start;
import lombok.Data;

@Data
public class StartDto {
    private Long id;

    private RoundDto round;

    private RouteDto route;

    private ParticipantDto participant;

    private String result;

}
