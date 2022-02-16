package com.teleclimb.rest.entities;

import com.teleclimb.enums.StartsGenerationMethod;
import lombok.*;

import javax.persistence.*;


@Builder //todo remove
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Getter
@Setter
@Table(name = "round")
public class RoundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sequenceNumber;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private CompetitionEntity competition;

    private String name;

    private Integer numberOfRoutes;

    private Integer maxParticipants;

    private String resultCalculatingFunction;

    @Enumerated(value = EnumType.STRING)
    private StartsGenerationMethod startsGenerationMethod;

}
