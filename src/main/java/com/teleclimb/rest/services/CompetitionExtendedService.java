package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.CompetitionDto;
import com.teleclimb.rest.dto.custom.CompetitionWithParticipantsList;
import com.teleclimb.rest.dto.RoundDto;
import com.teleclimb.rest.dto.custom.ParticipantRawDto;
import com.teleclimb.rest.repositories.CompetitionRepository;
import com.teleclimb.rest.repositories.ParticipantRepository;
import com.teleclimb.rest.repositories.RoundRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CompetitionExtendedService(ModelMapper mapper, ParticipantRepository contestantRepo, RoundRepository roundRepo, CompetitionRepository competitionRepo){
    public CompetitionWithParticipantsList getAllParticipants(Long competitionId) {
        List<ParticipantRawDto> participants = contestantRepo
                .findByCompetitionId(competitionId)
                .stream()
                .map(p -> mapper.map(p, ParticipantRawDto.class))
                .toList();

        CompetitionDto competition = mapper.map(competitionRepo.getById(competitionId), CompetitionDto.class);


        CompetitionWithParticipantsList dto = new CompetitionWithParticipantsList();
        dto.setParticipants(participants);
        dto.setCompetition(competition);
        return dto;
    }

    public List<RoundDto> getAllRounds(Long competitionId) {
        return roundRepo
                .findByCompetitionId(competitionId)
                .stream()
                .map(r -> mapper.map(r, RoundDto.class))
                .toList();
    }
}
