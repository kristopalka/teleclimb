package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.InternalServerError;
import com.teleclimb.rest.dto.CompetitionDto;
import com.teleclimb.rest.dto.ParticipantRawDto;
import com.teleclimb.rest.dto.RoundRawDto;
import com.teleclimb.rest.dto.custom.CompetitionWithParticipantsList;
import com.teleclimb.rest.dto.custom.CompetitionWithRoundsList;
import com.teleclimb.rest.entities.Competition;
import com.teleclimb.rest.entities.Round;
import com.teleclimb.rest.repositories.CompetitionRepository;
import com.teleclimb.rest.repositories.ParticipantRepository;
import com.teleclimb.rest.repositories.RoundRepository;
import com.teleclimb.util.RoundsGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CompetitionManagementService(ModelMapper mapper, ParticipantRepository contestantRepo,
                                           RoundRepository roundRepo, CompetitionRepository competitionRepo) {
    public CompetitionWithParticipantsList getAllParticipants(Integer competitionId) {
        CompetitionWithParticipantsList dto = new CompetitionWithParticipantsList();

        dto.setParticipants(contestantRepo.findByCompetitionId(competitionId)
                .stream()
                .map(p -> mapper.map(p, ParticipantRawDto.class))
                .toList());

        dto.setCompetition(mapper.map(competitionRepo.getById(competitionId), CompetitionDto.class));

        return dto;
    }

    public CompetitionWithRoundsList getAllRounds(Integer competitionId) {
        CompetitionWithRoundsList dto = new CompetitionWithRoundsList();

        dto.setRounds(roundRepo.findByCompetitionId(competitionId)
                .stream()
                .map(r -> mapper.map(r, RoundRawDto.class))
                .toList());

        dto.setCompetition(mapper.map(competitionRepo.getById(competitionId), CompetitionDto.class));

        return dto;
    }


    public CompetitionWithRoundsList generateRounds(Integer competitionId) {
        try {
            if (getAllRounds(competitionId).getRounds().size() != 0)
                throw new RuntimeException("there are already rounds for this competition");

            return tryToGenerateRounds(competitionId);
        } catch (Exception e) {
            throw new InternalServerError("Something went wrong while generating rounds: '" + e.getMessage() + "'");
        }
    }

    private CompetitionWithRoundsList tryToGenerateRounds(Integer competitionId) {
        Competition competition = competitionRepo.getById(competitionId);

        RoundsGenerator generator = new RoundsGenerator(competition);
        List<Round> rounds = generator.generate();

        roundRepo.saveAll(rounds);

        return getAllRounds(competitionId);
    }
}
