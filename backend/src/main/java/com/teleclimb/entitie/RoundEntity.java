package com.teleclimb.entitie;

import com.teleclimb.dto.enums.ResultCalculatingFunction;
import com.teleclimb.dto.enums.RoundState;
import com.teleclimb.dto.enums.StartsGenerationMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "round")
public class RoundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private CompetitionEntity competition;

    private Integer sequenceNumber;

    private String name;

    private Integer numberOfRoutes;

    private Integer maxParticipants;

    @Enumerated(value = EnumType.STRING)
    private StartsGenerationMethod startsGenerationMethod;

    @Enumerated(value = EnumType.STRING)
    private ResultCalculatingFunction resultCalculatingFunction;

    @Enumerated(value = EnumType.STRING)
    private RoundState state;
}
