package com.teleclimb.rest.start;

import com.teleclimb.enums.CompetitionType;
import com.teleclimb.rest.contestant.Contestant;
import com.teleclimb.rest.round.Round;
import com.teleclimb.rest.route.Route;
import lombok.Data;

import javax.persistence.*;

@Data
public class StartDto {
    private Long id;

    private Round round;

    private Route route;

    private Contestant contestant;

    private String result; // if null, start have not done yet
}
