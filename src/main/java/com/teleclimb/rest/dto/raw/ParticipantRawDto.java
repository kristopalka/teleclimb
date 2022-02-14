package com.teleclimb.rest.dto.raw;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ParticipantRawDto {
    private Long id;

    private Long competitionId;

    private String name;

    private String lastName;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;

}
