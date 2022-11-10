package com.teleclimb.service.start;

import com.teleclimb.controller.responses.error.exception.BadRequestException;
import com.teleclimb.controller.responses.error.exception.InternalServerError;
import com.teleclimb.controller.responses.error.exception.TeleclimbException;
import com.teleclimb.dto.enums.StartsGenerationMethod;
import com.teleclimb.dto.model.Participant;
import com.teleclimb.dto.model.RefereePosition;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.Start;
import com.teleclimb.service.ParticipantService;
import com.teleclimb.service.RefereePositionService;
import com.teleclimb.service.round.RoundService;
import com.teleclimb.util.starts_generators.ClassicLeadEliminationsGenerator;
import com.teleclimb.util.starts_generators.ClassicLeadFinalsGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record StartsGeneratingService(RoundService roundService, ParticipantService participantService,
                                      StartService startService, RefereePositionService positionService) {

    public void tryToGenerateStarts(Integer roundId) {
        try {
            checkCorrectnessAndGenerate(roundId);
        } catch (TeleclimbException e) {
            throw new InternalServerError("Something went wrong while generating starts: '" + e.getMessage() + "'");
        }
    }

    private void checkCorrectnessAndGenerate(Integer roundId) {
        Round round = roundService.get(roundId);
        List<RefereePosition> positions = positionService.getAllByRoundId(roundId);
        List<Participant> participants = participantService.getParticipantsByRoundId(roundId);

        if (ifAnyOfPositionsHasStarts(positions))
            throw new BadRequestException("there are already starts for this round, probably generations was done before");
        if (positions.size() != round.getNumberOfRoutes())
            throw new BadRequestException("number of routes in this round is not match expected number: is: " + positions.size() + " expected: " + round.getNumberOfRoutes());


        List<Start> starts = useCorrectGenerator(round, participants, positions);

        startService.addAll(starts);
    }

    private boolean ifAnyOfPositionsHasStarts(List<RefereePosition> positions) {
        for (RefereePosition position : positions) {
            if (startService.getAllByRefereePositionId(position.getId()).size() != 0) return true;
        }
        return false;
    }

    public List<Start> useCorrectGenerator(Round round, List<Participant> participants, List<RefereePosition> positions) {
        StartsGenerationMethod method = round.getStartsGenerationMethod();

        switch (method) {
            case LEAD_CLASSIC_ELIMINATIONS -> {
                ClassicLeadEliminationsGenerator generator = new ClassicLeadEliminationsGenerator();
                return generator.generate(round, participants, positions);
            }
            case LEAD_CLASSIC_FINAL -> {
                ClassicLeadFinalsGenerator generator = new ClassicLeadFinalsGenerator();
                return generator.generate(round, participants, positions);
            }
            default -> throw new BadRequestException("wrong formula identifier");
        }
    }
}

