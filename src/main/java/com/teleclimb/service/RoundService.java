package com.teleclimb.service;

import com.teleclimb.controller.responses.error.exception.BadRequestException;
import com.teleclimb.controller.responses.error.exception.NotFoundException;
import com.teleclimb.dto.enums.RoundState;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.Start;
import com.teleclimb.entitie.RoundEntity;
import com.teleclimb.repositorie.RoundRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public record RoundService(ModelMapper mapper, RoundRepository roundRepo, RefereePositionService positionService,
                           StartService startService) {

    // --------------------------------- GET ---------------------------------

    public Round get(Integer id) {
        RoundEntity roundEntity = roundRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found round with id: " + id));
        return mapper.map(roundEntity, Round.class);
    }

    public Round getByCompetitionIdAndSequenceNumber(Integer competitionId, Integer sequenceNumber) {
        List<Round> rounds = getAllByCompetitionId(competitionId);
        return rounds.stream()
                .filter(r -> (Objects.equals(r.getSequenceNumber(), sequenceNumber)))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Not found round with sequence number: " + sequenceNumber + " for competition with id: " + competitionId));
    }

    public List<Round> getAll() {
        return roundRepo.findAll().stream().map(entity -> mapper.map(entity, Round.class)).toList();
    }

    public List<Round> getAllByCompetitionId(Integer competitionId) {
        return roundRepo.findByCompetitionId(competitionId).stream().map(entity -> mapper.map(entity, Round.class)).toList();
    }

    public Boolean areAllResultsInserted(Integer id) {
        List<Start> starts = startService.getAllByRoundId(id);

        for (Start start : starts) {
            if (start.getResult() == null) return false;
        }
        return true;
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
        Round round = get(roundId);
        if (round.getState() != RoundState.NOT_STARTED) throw new BadRequestException("Can not modify started round");
        if (positionService.getAllByRoundId(roundId).size() >= get(roundId).getNumberOfRoutes())
            throw new BadRequestException("There is max number of referees positions created to round with id: " + routeId);

        positionService.addPosition(roundId, routeId);
    }

    public void removeRoute(Integer roundId, Integer routeId) {
        Round round = get(roundId);
        if (round.getState() != RoundState.NOT_STARTED) throw new BadRequestException("Can not modify started round");

        positionService.removePosition(roundId, routeId);
    }

    public void setState(Integer roundId, RoundState state) {
        Round round = get(roundId);
        round.setState(state);

        roundRepo.save(mapper.map(round, RoundEntity.class));
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
