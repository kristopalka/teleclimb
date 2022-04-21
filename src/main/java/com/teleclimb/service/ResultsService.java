package com.teleclimb.service;

import com.teleclimb.controller.responses.error.exception.NotImplementedException;
import com.teleclimb.dto.model.Participant;
import com.teleclimb.dto.model.competition_results.CompetitionResults;
import com.teleclimb.dto.model.competition_results.ParticipantData;
import com.teleclimb.service.competition.CompetitionService;
import com.teleclimb.service.start.StartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ResultsService(CompetitionService competitionService, ParticipantService participantService,
                             StartService startService) {
    public CompetitionResults getResults(Integer competitionId) {
        CompetitionResults results = new CompetitionResults();
        results.setCompetition(competitionService.get(competitionId));
        results.setParticipantsData(getParticipantsData(competitionId));

        return results;
    }

    private List<ParticipantData> getParticipantsData(Integer competitionId) {
        List<Participant> participants = participantService.getAllByCompetitionId(competitionId);

        throw new NotImplementedException("XD");
    }
}
