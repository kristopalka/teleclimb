package com.teleclimb.rest.contestant;

import com.teleclimb.rest.competition.Competition;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContestantDto {
    private Long id;

    private Competition competition;

    private String name;

    private String lastName;

    private String startNumber;

    private String clubName;

    private LocalDate birthDate;
}
