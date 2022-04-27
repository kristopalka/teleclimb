package com.teleclimb.service;

import com.teleclimb.controller.responses.error.exception.NotImplementedException;
import com.teleclimb.dto.enums.RoundState;
import com.teleclimb.dto.model.Participant;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.results.CompetitionResults;
import com.teleclimb.dto.model.results.ParticipantResults;
import com.teleclimb.service.round.RoundService;
import com.teleclimb.service.start.StartService;
import com.teleclimb.util.TwoRoutesLeadEliminationsParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public record ResultsService(CompetitionService competitionService, ParticipantService participantService,
                             StartService startService, RoundService roundService,
                             RefereePositionService positionService) {

    public CompetitionResults getResults(Integer competitionId) {
        CompetitionResults results = new CompetitionResults();
        results.setCompetition(competitionService.get(competitionId));
        results.setRounds(roundService.getAllByCompetitionId(competitionId));
        results.setParticipantsData(getParticipantsData(competitionId));

        return results;
    }

    private List<ParticipantResults> getParticipantsData(Integer competitionId) {
        List<Round> rounds = getSortedRounds(competitionId);
        List<ParticipantResults> participantsResults = generateList(competitionId);

        for (Round round : rounds) {
            if (round.getState() == RoundState.NOT_STARTED) break;
            participantsResults = generateResultsForRound(participantsResults, round);
        }

        return participantsResults;
    }

    private List<ParticipantResults> generateList(Integer competitionId) {
        List<Participant> participants = participantService.getAllByCompetitionId(competitionId);
        List<ParticipantResults> participantsResults = new ArrayList<>();

        for (Participant participant : participants) {
            ParticipantResults participantResults = new ParticipantResults();
            participantResults.setParticipant(participant);

            participantsResults.add(participantResults);
        }
        return participantsResults;
    }

    private List<Round> getSortedRounds(Integer competitionId) {
        List<Round> rounds = roundService.getAllByCompetitionId(competitionId);

        rounds.sort(Comparator.comparing(Round::getSequenceNumber));

        return rounds;
    }


    private List<ParticipantResults> generateResultsForRound(List<ParticipantResults> participantResults, Round round) {
        switch (round.getResultCalculatingFunction()) {
            case TWO_ROUTES_LEAD_ELIMINATIONS: {
                TwoRoutesLeadEliminationsParser parser = new TwoRoutesLeadEliminationsParser(participantResults, round, positionService, startService);
                return parser.process();
            }
        }
        throw new NotImplementedException("This type of result calculating function is not supported");
    }
}
