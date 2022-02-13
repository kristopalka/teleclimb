package com.teleclimb.rest.start;

import com.teleclimb.rest.contestant.Contestant;
import com.teleclimb.rest.round.Round;
import com.teleclimb.rest.route.Route;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Start {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "contestant_id")
    private Contestant contestant;

    private String result;


    public StartDto toDto() {
        StartDto dto = new StartDto();

        dto.setId(this.getId());
        dto.setRound(this.getRound());
        dto.setRoute(this.getRoute());
        dto.setContestant(this.getContestant());
        dto.setResult(this.getResult());

        return dto;
    }
}