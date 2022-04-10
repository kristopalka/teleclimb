package com.teleclimb.rest.services.upperlevel;

import com.teleclimb.rest.dto.Participant;
import com.teleclimb.rest.dto.RefereePosition;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.dto.Start;
import com.teleclimb.rest.responses.error.exception.InternalServerError;
import com.teleclimb.rest.services.ParticipantService;
import com.teleclimb.rest.services.RefereePositionService;
import com.teleclimb.rest.services.RoundService;
import com.teleclimb.rest.services.StartService;
import com.teleclimb.util.StartsGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record StartsGeneratingService(RoundService roundService, ParticipantService participantService,
                                      StartService startService, RefereePositionService positionService) {

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

