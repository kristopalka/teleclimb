package com.teleclimb.rest.round;

import com.teleclimb.rest.competition.Competition;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
public class RoundDto {
    private Long id;

    private Competition competition;

    private String name;

    private Integer maxContestants;
}
