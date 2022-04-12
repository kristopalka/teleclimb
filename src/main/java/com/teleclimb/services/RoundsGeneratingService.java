package com.teleclimb.services;

import com.teleclimb.controllers.responses.error.exception.InternalServerError;
import com.teleclimb.dto.Competition;
import com.teleclimb.dto.Formula;
import com.teleclimb.dto.Round;
import com.teleclimb.util.RoundsGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoundsGeneratingService(RoundService roundService,
                                      CompetitionService competitionService, FormulaService formulaService) {

    public List<Round> generateRounds(Integer competitionId) {
        try {
            return tryToGenerateRounds(competitionId);
        } catch (Exception e) {
            throw new InternalServerError("Something went wrong while generating rounds: '" + e.getMessage() + "'");
        }
    }

    private List<Round> tryToGenerateRounds(Integer competitionId) {
        Competition competition = competitionService.get(competitionId);
        Formula formula = formulaService.get(competition.getFormulaId());

        if (roundService.getAllByCompetitionId(competitionId).size() != 0)
            throw new RuntimeException("there are already rounds for this competition. Probably generations was done before.");


        RoundsGenerator generator = new RoundsGenerator(competition, formula);
        List<Round> rounds = generator.generate();

        roundService.addAll(rounds);

        return roundService.getAllByCompetitionId(competitionId);
    }

}
