package com.teleclimb.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantWithMeta {
    private Integer id;

    private Integer competitionId;

    private Integer topRoundNumber;

    private Integer previousRoundPlace;

    private List<Meta> meta;

    // participant data
    private String name;

    private String lastName;

    private Integer rankingPosition;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;

}
