package com.teleclimb.rest.services;

import com.teleclimb.enums.Discipline;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.entities.RoundEntity;
import com.teleclimb.rest.repositories.RoundRepository;
import com.teleclimb.rest.responses.error.exception.BadRequestException;
import com.teleclimb.rest.services.custom.ValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoundService(ModelMapper mapper, RoundRepository roundRepo, RoundRouteLinkService linkService,
                           ValidationService validationService) {

    // --------------------------------- GET ---------------------------------

    public Round get(Integer id) {
        validationService.validateRoundId(id);
        RoundEntity roundEntity = roundRepo.findById(id).orElseThrow();
        return toDto(roundEntity);
    }

    public Discipline getDiscipline(Integer id) {
        validationService.validateRoundId(id);
        RoundEntity roundEntity = roundRepo.findById(id).orElseThrow();
        return roundEntity.getCompetition().getFormula().getDiscipline();
    }

    public List<Round> getAll() {
        return roundRepo.findAll().stream().map(this::toDto).toList();
    }

    public List<Round> getAllByCompetitionId(Integer competitionId) {
        return roundRepo.findByCompetitionId(competitionId).stream().map(this::toDto).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public Round add(Round round) {
        validateRound(round);

        return toDto(roundRepo.save(toEntity(round)));
    }

    private void validateRound(Round round) {
        //todo round validation
    }

    public void addAll(List<Round> rounds) {
        rounds.forEach(this::add);
    }


    // --------------------------------- UPDATE ---------------------------------

    public void addRoute(Integer roundId, Integer routeId) {
        if (linkService.getAllLinksByRoundId(roundId).size() >= get(roundId).getNumberOfRoutes())
            throw new BadRequestException("There is max number of links to round with id: " + routeId);

        linkService.addLink(roundId, routeId);
    }

    public void removeRoute(Integer roundId, Integer routeId) {
        linkService.removeLink(roundId, routeId);
    }


    // --------------------------------- DELETE ---------------------------------

    public void delete(Integer id) {
        //todo remove all starts and links
        roundRepo.deleteById(id);
    }

    public void deleteAllByCompetitionId(Integer competitionId) {
        getAllByCompetitionId(competitionId).forEach(p -> delete(p.getId()));
    }


    // --------------------------------- MAPPING ---------------------------------

    private Round toDto(RoundEntity entity) {
        return mapper.map(entity, Round.class);
    }

    private RoundEntity toEntity(Round dto) {
        return mapper.map(dto, RoundEntity.class);
    }

}
