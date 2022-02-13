package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.ContestantDto;
import com.teleclimb.rest.dto.RoundDto;
import com.teleclimb.rest.entities.Contestant;
import com.teleclimb.rest.entities.Round;
import com.teleclimb.rest.repositories.ContestantRepository;
import com.teleclimb.rest.repositories.RoundRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record CompetitionExtendedService(ContestantRepository contestantRepo, RoundRepository roundRepo){
    public List<ContestantDto> getAllContestants(Long competitionId) {
        return contestantRepo.findByCompetitionId(competitionId).stream().map(Contestant::toDto).collect(Collectors.toList());
    }

    public List<RoundDto> getAllRounds(Long competitionId) {
        return roundRepo.findByCompetitionId(competitionId).stream().map(Round::toDto).collect(Collectors.toList());
    }
}
