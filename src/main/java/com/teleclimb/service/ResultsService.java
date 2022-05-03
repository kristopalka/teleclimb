package com.teleclimb.service;

import com.teleclimb.controller.responses.error.exception.NotFoundException;
import com.teleclimb.controller.responses.error.exception.NotImplementedException;
import com.teleclimb.dto.model.ParticipantWithMeta;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.results.CompetitionResults;
import com.teleclimb.service.round.RoundService;
import com.teleclimb.service.start.StartService;
import com.teleclimb.util.results_parsers.OneRouteLeadFinalsParser;
import com.teleclimb.util.results_parsers.TwoRoutesLeadEliminationsParser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ResultsService(CompetitionService competitionService, ParticipantService participantService,
                             StartService startService, RoundService roundService,
                             RefereePositionService positionService) {

    public CompetitionResults getResults(Integer competitionId) {
        CompetitionResults results = new CompetitionResults();
        results.setCompetition(competitionService.get(competitionId));
        results.setRounds(roundService.getAllByCompetitionId(competitionId));
        results.setParticipantsData(getAllParticipantData(competitionId));

        return results;
    }

    public void calculateAndSaveResult(Integer roundId) {
        Round currentRound = roundService.get(roundId);
        List<ParticipantWithMeta> participantsResults = getAllParticipantData(currentRound.getCompetitionId());

        participantsResults = generateResultsForRound(participantsResults, currentRound);

        participantService.updateDataAndMetaForAll(participantsResults);
    }


    private List<ParticipantWithMeta> getAllParticipantData(Integer competitionId) {
        List<ParticipantWithMeta> participants = participantService.getAllParticipantsWithMetaByCompetitionId(competitionId);

        try {
            Round currentRound = roundService.getByCompetitionIdRoundInProgress(competitionId);
            participants = generateResultsForRound(participants, currentRound);
            ;
            return participants;
        } catch (NotFoundException e) {
            return participants;
        }
    }


    private List<ParticipantWithMeta> generateResultsForRound(List<ParticipantWithMeta> participantResults, Round round) {
        switch (round.getResultCalculatingFunction()) {
            case TWO_ROUTES_LEAD_ELIMINATIONS: {
                TwoRoutesLeadEliminationsParser parser = new TwoRoutesLeadEliminationsParser(participantResults, round, positionService, startService);
                return parser.process();
            }
            case ONE_ROUTE_LEAD_FINAL: {
                OneRouteLeadFinalsParser parser = new OneRouteLeadFinalsParser(participantResults, round, positionService, startService);
                return parser.process();
            }
        }
        throw new NotImplementedException("This result calculating function is not supported");
    }
}
