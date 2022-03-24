package com.teleclimb.rest.services;

import com.teleclimb.rest.repositories.*;
import com.teleclimb.rest.responses.error.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public record ValidationService(CategoryRepository categoryRepo, CompetitionRepository competitionRepo,
                                FormulaRepository formulaRepo, ParticipantRepository participantRepo,
                                RefereePositionRepository positionRepo, RoundRepository roundRepo,
                                RouteRepository routeRepo, StartRepository startRepo) {

    public void validateCategoryId(Integer categoryId) {
        if (!categoryRepo.existsById(categoryId))
            throw new NotFoundException("Category with specific id: " + categoryId + " does not exist");
    }

    public void validateCompetitionId(Integer competitionId) {
        if (!competitionRepo.existsById(competitionId))
            throw new NotFoundException("Competition with specific id: " + competitionId + " does not exist");
    }

    public void validateFormulaId(Integer formulaId) {
        if (!formulaRepo.existsById(formulaId))
            throw new NotFoundException("Formula with specific id: " + formulaId + " does not exist");
    }

    public void validateParticipantId(Integer participantId) {
        if (!participantRepo.existsById(participantId))
            throw new NotFoundException("Participant with specific id: " + participantId + " does not exist");
    }

    public void validateRefereePositionId(Integer positionId) {
        if (!positionRepo.existsById(positionId))
            throw new NotFoundException("RefereePosition with specific id: " + positionId + " does not exist");
    }

    public void validateRoundId(Integer roundId) {
        if (!roundRepo.existsById(roundId))
            throw new NotFoundException("Round with specific id: " + roundId + " does not exist");
    }

    public void validateRouteId(Integer routeId) {
        if (!routeRepo.existsById(routeId))
            throw new NotFoundException("Route with specific id: " + routeId + " does not exist");
    }

    public void validateStartId(Integer startId) {
        if (!startRepo.existsById(startId))
            throw new NotFoundException("Start with specific id: " + startId + " does not exist");
    }
}
