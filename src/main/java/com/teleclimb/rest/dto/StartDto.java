package com.teleclimb.rest.dto;

import com.teleclimb.rest.entities.Contestant;
import com.teleclimb.rest.entities.Round;
import com.teleclimb.rest.entities.Route;
import com.teleclimb.rest.entities.Start;
import lombok.Data;

@Data
public class StartDto {
    private Long id;

    private Round round;

    private Route route;

    private Contestant contestant;

    private String result;

    public Start toEntity() {
        Start start = new Start();

        start.setId(this.getId());
        start.setRound(this.getRound());
        start.setRoute(this.getRoute());
        start.setContestant(this.getContestant());
        start.setResult(this.getResult());

        return start;
    }
}
