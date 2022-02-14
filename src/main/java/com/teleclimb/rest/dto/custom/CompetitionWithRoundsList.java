package com.teleclimb.rest.dto.custom;

import com.teleclimb.rest.dto.CompetitionDto;
import com.teleclimb.rest.dto.raw.RoundRawDto;
import lombok.Data;

import java.util.List;

@Data
public class CompetitionWithRoundsList {
    private CompetitionDto competition;

    private List<RoundRawDto> rounds;
}
