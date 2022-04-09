package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.entities.RoundEntity;
import com.teleclimb.rest.repositories.RoundRepository;
import com.teleclimb.rest.responses.error.exception.BadRequestException;
import com.teleclimb.rest.responses.error.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoundService(ModelMapper mapper, RoundRepository roundRepo, RefereePositionService positionService) {

    // --------------------------------- GET ---------------------------------

    public Round get(Integer id) {
        RoundEntity roundEntity = roundRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found round with id: " + id));
        return mapper.map(roundEntity, Round.class);
    }

    public List<Round> getAll() {
        return roundRepo.findAll().stream().map(entity -> mapper.map(entity, Round.class)).toList();
    }

    public List<Round> getAllByCompetitionId(Integer competitionId) {
        return roundRepo.findByCompetitionId(competitionId).stream().map(entity -> mapper.map(entity, Round.class)).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public Round add(Round round) {
        validateRound(round);

        return mapper.map(roundRepo.save(mapper.map(round, RoundEntity.class)), Round.class);
    }

    private void validateRound(Round round) {
        //todo round validation
    }

    public void addAll(List<Round> rounds) {
        rounds.forEach(this::add);
    }


    // --------------------------------- UPDATE ---------------------------------

    public void addRoute(Integer roundId, Integer routeId) {
        if (positionService.getAllByRoundId(roundId).size() >= get(roundId).getNumberOfRoutes())
            throw new BadRequestException("There is max number of referees positions created to round with id: " + routeId);

        positionService.addPosition(roundId, routeId);
    }

    public void removeRoute(Integer roundId, Integer routeId) {
        positionService.removePosition(roundId, routeId);
    }


    // --------------------------------- DELETE ---------------------------------

    public void delete(Integer id) {
        //todo remove all starts and referee positions
        roundRepo.deleteById(id);
    }

    public void deleteAllByCompetitionId(Integer competitionId) {
        getAllByCompetitionId(competitionId).forEach(p -> delete(p.getId()));
    }
}
