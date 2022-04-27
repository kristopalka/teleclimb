package com.teleclimb.service.round;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.controller.responses.error.exception.InternalServerError;
import com.teleclimb.dto.enums.RoundState;
import com.teleclimb.dto.model.Competition;
import com.teleclimb.dto.model.Round;
import com.teleclimb.service.FormulaService;
import com.teleclimb.service.competition.CompetitionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public record RoundsGeneratingService(RoundService roundService, CompetitionService competitionService,
                                      FormulaService formulaService) {
    private static final Gson gson = GsonConfig.gson();

    public List<Round> generateRounds(Integer competitionId) {
        try {
            return tryToGenerateRounds(competitionId);
        } catch (Exception e) {
            throw new InternalServerError("Something went wrong while generating rounds: '" + e.getMessage() + "'");
        }
    }

    private List<Round> tryToGenerateRounds(Integer competitionId) {
        Competition competition = competitionService.get(competitionId);

        if (roundService.getAllByCompetitionId(competitionId).size() != 0)
            throw new RuntimeException("there are already rounds for this competition. Probably generations was done before.");


        List<Round> rounds = extractFromConfig(competition);
        roundService.addAll(rounds);

        return roundService.getAllByCompetitionId(competitionId);
    }

    public List<Round> extractFromConfig(Competition competition) {
        String jsonConfiguration = formulaService.get(competition.getFormulaId()).getJsonConfiguration();

        List<Round> rounds = Arrays.stream(gson.fromJson(jsonConfiguration, Round[].class)).toList();

        for (Round round : rounds) {
            round.setCompetitionId(competition.getId());
            round.setState(RoundState.NOT_STARTED);
        }
        return rounds;
    }

}
