package com.teleclimb.service;

import com.teleclimb.dto.enums.RoundState;
import com.teleclimb.dto.model.Participant;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.results.CompetitionResults;
import com.teleclimb.dto.model.results.ParticipantData;
import com.teleclimb.service.competition.CompetitionService;
import com.teleclimb.service.round.RoundService;
import com.teleclimb.service.start.StartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public record ResultsService(CompetitionService competitionService, ParticipantService participantService,
                             StartService startService, RoundService roundService) {

    public CompetitionResults getResults(Integer competitionId) {
        CompetitionResults results = new CompetitionResults();
        results.setCompetition(competitionService.get(competitionId));
        results.setRounds(roundService.getAllByCompetitionId(competitionId));
        results.setParticipantsData(getParticipantsData(competitionId));

        return results;
    }

    private List<ParticipantData> getParticipantsData(Integer competitionId) {
        List<Round> rounds = getSortedRounds(competitionId);
        List<ParticipantData> participantsData = prepareParticipantsData(competitionId);

        for (Round round : rounds) {
            if (round.getState() == RoundState.NOT_STARTED) break;
            participantsData = sortAndAddData(participantsData, round);
        }

        return participantsData;
    }

    private List<Round> getSortedRounds(Integer competitionId) {
        List<Round> rounds = roundService.getAllByCompetitionId(competitionId);

        rounds.sort(Comparator.comparing(Round::getSequenceNumber));

        return rounds;
    }

    private List<ParticipantData> prepareParticipantsData(Integer competitionId) {
        List<Participant> participants = participantService.getAllByCompetitionId(competitionId);
        List<ParticipantData> participantsData = new ArrayList<>();

        for (Participant participant : participants) {
            ParticipantData participantData = new ParticipantData();
            participantData.setId(participant.getId());
            participantData.setName(participant.getName());
            participantData.setLastName(participant.getLastName());
            participantData.setClubName(participant.getClubName());
            participantData.setStartNumber(participant.getStartNumber());

            participantsData.add(participantData);
        }
        return participantsData;
    }

    private List<ParticipantData> sortAndAddData(List<ParticipantData> participantsData, Round round) {
        switch (round.getResultCalculatingFunction()) {
            case TWO_ROUTES_LEAD_ELIMINATIONS:
        }
        return null;
    }
}
