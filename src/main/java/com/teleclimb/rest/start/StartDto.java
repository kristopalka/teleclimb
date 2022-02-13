package com.teleclimb.rest.start;

import com.teleclimb.rest.contestant.Contestant;
import com.teleclimb.rest.round.Round;
import com.teleclimb.rest.route.Route;
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
