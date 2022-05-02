package com.teleclimb.service;

import com.teleclimb.controller.responses.error.exception.NotImplementedException;
import com.teleclimb.dto.enums.RoundState;
import com.teleclimb.dto.model.ParticipantWithMeta;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.results.CompetitionResults;
import com.teleclimb.service.round.RoundService;
import com.teleclimb.service.start.StartService;
import com.teleclimb.util.results_parsers.TwoRoutesLeadEliminationsParser;
import org.springframework.stereotype.Service;

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
        results.setParticipantsData(participantService.getAllParticipantsWithMetaByCompetitionId(competitionId)
                .stream()
                .sorted(Comparator.comparing(ParticipantWithMeta::getPreviousRoundPlace))
                .toList());

        return results;
    }

    public void calculateResultsAndUpdate(Integer competitionId) {
        List<Round> rounds = getSortedRounds(competitionId);
        List<ParticipantWithMeta> participantsResults = participantService.getAllParticipantsWithMetaByCompetitionId(competitionId);

        for (Round round : rounds) {
            if (round.getState() == RoundState.NOT_STARTED) break;
            participantsResults = generateResultsForRound(participantsResults, round);
        }

        participantService.updateDataAndMetaForAll(participantsResults);
    }


    private List<Round> getSortedRounds(Integer competitionId) {
        List<Round> rounds = roundService.getAllByCompetitionId(competitionId);
        return rounds.stream().sorted(Comparator.comparing(Round::getSequenceNumber)).toList();
    }


    private List<ParticipantWithMeta> generateResultsForRound(List<ParticipantWithMeta> participantResults, Round round) {
        switch (round.getResultCalculatingFunction()) {
            case TWO_ROUTES_LEAD_ELIMINATIONS: {
                TwoRoutesLeadEliminationsParser parser = new TwoRoutesLeadEliminationsParser(participantResults, round, positionService, startService);
                return parser.process();
            }
        }
        throw new NotImplementedException("This type of result calculating function is not supported");
    }
}
