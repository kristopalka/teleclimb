package com.teleclimb.util;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.enums.RoundState;
import com.teleclimb.rest.dto.Competition;
import com.teleclimb.rest.dto.Formula;
import com.teleclimb.rest.dto.Round;

import java.util.Arrays;
import java.util.List;

public class RoundsGenerator {
    private final Competition competition;
    private final Formula formula;
    private final Gson gson;

    public RoundsGenerator(Competition competition, Formula formula) {
        this.competition = competition;
        this.formula = formula;
        gson = GsonConfig.gson();
    }

    public List<Round> generate() {
        String jsonConfiguration = formula.getJsonConfiguration();

        List<Round> rounds = Arrays.stream(gson.fromJson(jsonConfiguration, Round[].class)).toList();

        for (Round round : rounds) {
            round.setCompetitionId(competition.getId());
            round.setState(RoundState.NOT_STARTED);
        }
        return rounds;
    }
}
