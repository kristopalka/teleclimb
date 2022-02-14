package com.teleclimb.rest.entities;

import com.teleclimb.rest.dto.StartDto;
import lombok.Data;
import springfox.documentation.swagger2.mappers.ModelMapper;
import springfox.documentation.swagger2.mappers.ModelMapperImpl;

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
    @JoinColumn(name = "participant_id")
    private Participant participant;

    private String result;

}