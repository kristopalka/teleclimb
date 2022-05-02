package com.teleclimb.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    private Integer id;

    private Integer competitionId;

    private Integer topRoundNumber;

    private Integer previousRoundPlace;

    // participant data
    private String name;

    private String lastName;

    private Integer rankingPosition;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;

}
