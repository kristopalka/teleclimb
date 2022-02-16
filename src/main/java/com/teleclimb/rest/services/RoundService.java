package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.dto.Route;
import com.teleclimb.rest.entities.RoundEntity;
import com.teleclimb.rest.repositories.RoundRepository;
import com.teleclimb.rest.repositories.StartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record RoundService(ModelMapper mapper, RoundRepository roundRepo, StartRepository startRepo,
                           RoundRouteLinkService linkService, ParticipantService participantService) {

    public List<Round> getAll() {
        return roundRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Round> getAllByCompetitionId(Integer competitionId) {
        return roundRepo.findByCompetitionId(competitionId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Round get(Integer id) {
        RoundEntity roundEntity = roundRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found round with id: " + id));

        return mapper.map(roundEntity, Round.class);
    }

    public void addAll(List<Round> rounds) {
        rounds.forEach(this::newRoundValidation);
        rounds.forEach(r -> roundRepo.save(toEntity(r)));
    }

    private void newRoundValidation(Round round) {
        //todo round validation
    }

    private Round toDto(RoundEntity entity) {
        return mapper.map(entity, Round.class);
    }

    private RoundEntity toEntity(Round dto) {
        return mapper.map(dto, RoundEntity.class);
    }


    public List<Route> getRoutes(Integer roundId) {
        return linkService.getAllRoutesForRoundId(roundId);
    }

    public void linkRoute(Integer id, Integer routeId) {
        Round round = get(id);

        if (getRoutes(id).size() >= round.getNumberOfRoutes())
            throw new BadRequestException("There is max number of links to round with id: " + id);

        linkService.addLink(id, routeId);
    }

    public void unlinkRoute(Integer id, Integer routeId) {
        linkService.removeLink(id, routeId);
    }

}
