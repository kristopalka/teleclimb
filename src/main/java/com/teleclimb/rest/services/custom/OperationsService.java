package com.teleclimb.rest.services.custom;

import com.teleclimb.rest.dto.*;
import com.teleclimb.rest.responses.error.exception.InternalServerError;
import com.teleclimb.rest.services.*;
import com.teleclimb.util.RoundsGenerator;
import com.teleclimb.util.StartsGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record OperationsService(RoundService roundService,
                                CompetitionService competitionService, FormulaService formulaService,
                                ParticipantService participantService,
                                StartService startService, RouteService routeService) {

    // --------------------------------- ROUNDS GENERATION ---------------------------------

    public List<Round> generateRounds(Integer competitionId) {
        try {
            if (roundService.getAllByCompetitionId(competitionId).size() != 0)
                throw new RuntimeException("There are already rounds for this competition. Probably generations was done before.");

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


    // --------------------------------- STARTS GENERATION ---------------------------------

    public void generateStarts(Integer roundId) {
        try {
            if (startService.getAllByRoundId(roundId).size() != 0)
                throw new RuntimeException("There are already starts for this round. Probably generations was done before.");

            tryToGenerateStarts(roundId);
        } catch (Exception e) {
            throw new InternalServerError("Something went wrong while generating starts: '" + e.getMessage() + "'");
        }
    }

    private void tryToGenerateStarts(Integer roundId) {
        Round round = roundService.get(roundId);
        List<Route> routes = routeService.getAllByRoundId(roundId);
        List<Participant> participants = participantService.getParticipantsByRoundId(roundId);

        StartsGenerator generator = new StartsGenerator(round, participants, routes);
        List<Start> starts = generator.generate();

        startService.addAll(starts);
    }
}
