package com.teleclimb.rest.dto.custom;

import com.teleclimb.rest.dto.Competition;
import com.teleclimb.rest.dto.Round;
import lombok.Data;

import java.util.List;

@Data
public class CompetitionWithRoundsList {
    private Competition competition;

    private List<Round> rounds;
}
