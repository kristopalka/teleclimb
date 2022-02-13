package com.teleclimb.rest.dto;

import com.teleclimb.rest.entities.Start;
import lombok.Data;

@Data
public class StartDto {
    private Long id;

    private RoundDto round;

    private RouteDto route;

    private ContestantDto contestant;

    private String result;


    public Start toEntity() {
        Start start = new Start();

        start.setId(this.getId());
        start.setRound(this.getRound().toEntity());
        start.setRoute(this.getRoute().toEntity());
        start.setContestant(this.getContestant().toEntity());
        start.setResult(this.getResult());

        return start;
    }
}
