package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.*;
import com.teleclimb.rest.responses.error.exception.InternalServerError;
import com.teleclimb.util.RoundsGenerator;
import com.teleclimb.util.StartsGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record OperationsService(ModelMapper mapper, RoundService roundService,
                                CompetitionService competitionService, FormulaService formulaService,
                                RoundRouteLinkService linkService, ParticipantService participantService,
                                StartService startService) {

    public List<Round> generateRounds(Integer competitionId) {
        try {
            if (roundService.getAllByCompetitionId(competitionId).size() != 0)
                throw new RuntimeException("there are already rounds for this competition");

            return tryToGenerateRounds(competitionId);
        } catch (Exception e) {
            throw new InternalServerError("Something went wrong while generating rounds: '" + e.getMessage() + "'");
        }
    }

    private List<Round> tryToGenerateRounds(Integer competitionId) {
        Competition competition = competitionService.get(competitionId);
        Formula formula = formulaService.get(competition.getFormulaId());

        RoundsGenerator generator = new RoundsGenerator(competition, formula);
        List<Round> rounds = generator.generate();

        roundService.addAll(rounds);

        return roundService.getAllByCompetitionId(competitionId);
    }


    public void generateStarts(Integer roundId) {
        try {
            tryToGenerateStarts(roundId);
        } catch (Exception e) {
            throw new InternalServerError("Something went wrong while generating starts: '" + e.getMessage() + "'");
        }
    }

    private void tryToGenerateStarts(Integer roundId) {
        Round round = roundService.get(roundId);
        List<Route> routes = linkService.getAllRoutesForRoundId(roundId);
        List<Participant> participants = participantService.getParticipantsByRound(round);

        StartsGenerator generator = new StartsGenerator(round, participants, routes);
        List<Start> starts = generator.generate();

        startService.addAll(starts);
    }
}
