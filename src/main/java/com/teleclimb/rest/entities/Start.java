package com.teleclimb.rest.entities;

import com.teleclimb.rest.dto.StartDto;
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