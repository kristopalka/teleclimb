package com.teleclimb.app.rest.entity;

import com.teleclimb.app.enums.CompetitionType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private CompetitionType competitionType;

    private LocalDateTime timeLimit;
}
