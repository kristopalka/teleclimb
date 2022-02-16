package com.teleclimb.rest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ParticipantRawDto {
    private Integer id;

    private Integer competitionId;

    private Integer roundSequenceNumber;

    private String name;

    private String lastName;

    private Integer rankingPosition;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;

}
