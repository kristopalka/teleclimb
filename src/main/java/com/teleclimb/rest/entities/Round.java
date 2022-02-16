package com.teleclimb.rest.entities;

import com.teleclimb.enums.StartsGenerationMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sequenceNumber;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private String name;

    private Integer numberOfRoutes;

    private Integer maxParticipants;

    private String resultCalculatingFunction;

    @Enumerated(value = EnumType.STRING)
    private StartsGenerationMethod startsGenerationMethod;

}
