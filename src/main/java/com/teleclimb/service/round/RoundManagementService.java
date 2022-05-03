package com.teleclimb.service.round;

import com.teleclimb.controller.responses.error.exception.BadRequestException;
import com.teleclimb.controller.responses.error.exception.NotFoundException;
import com.teleclimb.dto.enums.RoundState;
import com.teleclimb.dto.model.Participant;
import com.teleclimb.dto.model.Round;
import com.teleclimb.service.CompetitionService;
import com.teleclimb.service.ParticipantService;
import com.teleclimb.service.ResultsService;
import com.teleclimb.service.start.StartsGeneratingService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;


@Service
public record RoundManagementService(RoundService roundService, StartsGeneratingService startsGeneratingService,
                                     ResultsService resultsService, CompetitionService competitionService,
                                     ParticipantService participantService) {
    public void startRound(Integer roundId) {
        Round round = roundService.get(roundId);
        if (round.getState() != RoundState.NOT_STARTED)
            throw new BadRequestException("Can not start round, it was started before");
        if (!isPreviousRoundFinished(round))
            throw new BadRequestException("Can not start round, previous did not finished");
        startsGeneratingService.tryToGenerateStarts(roundId);

        roundService.setState(roundId, RoundState.IN_PROGRESS);
    }

    private boolean isPreviousRoundFinished(Round round) {
        if (round.getSequenceNumber() == 0) return true;

        Round previousRound = roundService.getByCompetitionIdAndSequenceNumber(round.getCompetitionId(), round.getSequenceNumber() - 1);
        return previousRound.getState() == RoundState.FINISHED;
    }

    public void finishRound(Integer roundId) {
        Round round = roundService.get(roundId);
        if (round.getState() != RoundState.IN_PROGRESS)
            throw new BadRequestException("Can not finish round, which is not in progress");

        resultsService.calculateAndSaveResult(round.getId());

        moveBestParticipantsToNextRound(roundId);

        roundService.setState(roundId, RoundState.FINISHED);
    }

    private void moveBestParticipantsToNextRound(Integer roundId) {
        Round currentRound = roundService.get(roundId);
        try {
            Round nextRound = roundService.getByCompetitionIdAndSequenceNumber(currentRound.getCompetitionId(), 1 + currentRound.getSequenceNumber());
            List<Participant> participants = participantService.getParticipantsByRoundId(roundId).stream()
                    .sorted(Comparator.comparing(Participant::getPlace)).toList();

            int nextRoundNumber = nextRound.getMaxParticipants();

            for (int i = 0; i < nextRoundNumber; i++) {
                Integer participantId = participants.get(i).getId();
                participantService.incrementTopRoundSequenceNumber(participantId);
            }
            while (Objects.equals(participants.get(nextRoundNumber - 1).getPlace(), participants.get(nextRoundNumber).getPlace())) {
                participantService.incrementTopRoundSequenceNumber(participants.get(nextRoundNumber).getId());
                nextRoundNumber++;
            }
        } catch (NotFoundException ignored) {
        }
    }
}
