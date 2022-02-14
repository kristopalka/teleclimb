package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.CompetitionDto;
import com.teleclimb.rest.dto.custom.CompetitionWithParticipantsList;
import com.teleclimb.rest.dto.RoundDto;
import com.teleclimb.rest.dto.custom.CompetitionWithRoundsList;
import com.teleclimb.rest.dto.raw.ParticipantRawDto;
import com.teleclimb.rest.dto.raw.RoundRawDto;
import com.teleclimb.rest.repositories.CompetitionRepository;
import com.teleclimb.rest.repositories.ParticipantRepository;
import com.teleclimb.rest.repositories.RoundRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public record CompetitionExtendedService(ModelMapper mapper, ParticipantRepository contestantRepo, RoundRepository roundRepo, CompetitionRepository competitionRepo){
    public CompetitionWithParticipantsList getAllParticipants(Long competitionId) {
        CompetitionWithParticipantsList dto = new CompetitionWithParticipantsList();

        dto.setParticipants(contestantRepo.findByCompetitionId(competitionId)
                .stream()
                .map(p -> mapper.map(p, ParticipantRawDto.class))
                .toList());

        dto.setCompetition(mapper.map(competitionRepo.getById(competitionId), CompetitionDto.class));

        return dto;
    }

    public CompetitionWithRoundsList getAllRounds(Long competitionId) {
        CompetitionWithRoundsList dto = new CompetitionWithRoundsList();

        dto.setRounds(contestantRepo.findByCompetitionId(competitionId)
                .stream()
                .map(r -> mapper.map(r, RoundRawDto.class))
                .toList());

        dto.setCompetition(mapper.map(competitionRepo.getById(competitionId), CompetitionDto.class));

        return dto;
    }
}
