package com.teleclimb.util;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.rest.dto.CompetitionDto;
import com.teleclimb.rest.dto.RoundDto;

import java.util.ArrayList;
import java.util.List;

public class RoundsGenerator {
    private final CompetitionDto competition;
    private final List<RoundDto> rounds;

    public RoundsGenerator(CompetitionDto competition) {
        this.competition = competition;
        rounds = new ArrayList<>();
    }

    public List<RoundDto> generate() {
        throw new BadRequestException("not implemented yet");
    }
}
