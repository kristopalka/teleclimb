package com.teleclimb.rest.services.upperlevel;

import com.teleclimb.enums.RoundState;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.responses.error.exception.BadRequestException;
import com.teleclimb.rest.responses.error.exception.NotImplementedException;
import com.teleclimb.rest.services.RoundService;
import org.springframework.stereotype.Service;

@Service
public record RoundManagementService(RoundService roundService, StartsGeneratingService startsGeneratingService) {
    public void startRound(Integer roundId) {
        Round round = roundService.get(roundId);
        if (round.getState() != RoundState.NOT_STARTED)
            throw new BadRequestException("Can not start round, it was started before");

        startsGeneratingService.generateStarts(roundId);

        roundService.setState(roundId, RoundState.IN_PROGRESS);
    }

    public void finishRound(Integer roundId) {
        Round round = roundService.get(roundId);
        if (round.getState() != RoundState.IN_PROGRESS)
            throw new BadRequestException("Can not finish round, which is not in progress");

        //todo posortować zawodników w kolejności po wynikach w rundzie

        throw new NotImplementedException("not implemented");
    }
}
