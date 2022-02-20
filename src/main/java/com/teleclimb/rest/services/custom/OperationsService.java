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
                                StartService startService, RefereePositionService positionService) {

    // --------------------------------- ROUNDS GENERATION ---------------------------------

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


    // --------------------------------- STARTS GENERATION ---------------------------------

    public void generateStarts(Integer roundId) {
        try {
            tryToGenerateStarts(roundId);
        } catch (Exception e) {
            throw new InternalServerError("Something went wrong while generating starts: '" + e.getMessage() + "'");
        }
    }

    private void tryToGenerateStarts(Integer roundId) {
        Round round = roundService.get(roundId);
        List<RefereePosition> positions = positionService.getAllByRoundId(roundId);
        List<Participant> participants = participantService.getParticipantsByRoundId(roundId);

        if (ifAnyOfPositionsHasStarts(positions))
            throw new RuntimeException("there are already starts for this round, probably generations was done before");
        if (positions.size() != round.getNumberOfRoutes())
            throw new RuntimeException("number of routes in this round is not match expected number: is: " + positions.size() + " expected: " + round.getNumberOfRoutes());


        StartsGenerator generator = new StartsGenerator(round, participants, positions);
        List<Start> starts = generator.generate();

        startService.addAll(starts);
    }

    private boolean ifAnyOfPositionsHasStarts(List<RefereePosition> positions) {
        for (RefereePosition position : positions) {
            if (startService.getAllByRefereePositionId(position.getId()).size() != 0) return true;
        }
        return false;
    }
}
