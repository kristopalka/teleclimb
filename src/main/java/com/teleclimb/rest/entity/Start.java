package com.teleclimb.rest.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Start {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "id")
    private Contestant contestant;

    private String result; // if null, start have not done yet
}
//TODO bardzo ważna jest walidacja przy tworzeniu: czy contestant category i route competitiontype aplikują się do danego competition
