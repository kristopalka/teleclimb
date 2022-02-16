package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.InternalServerError;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.Participant;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.dto.Route;
import com.teleclimb.rest.entities.RoundEntity;
import com.teleclimb.rest.entities.StartEntity;
import com.teleclimb.rest.repositories.RoundRepository;
import com.teleclimb.rest.repositories.StartRepository;
import com.teleclimb.util.StartsGenerator;
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
                .map(r -> mapper.map(r, Round.class))
                .collect(Collectors.toList());
    }

    public Round get(Integer id) {
        RoundEntity roundEntity = roundRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found round with id: " + id));

        return mapper.map(roundEntity, Round.class);
    }

    public List<Route> getRoutes(Integer roundId) {
        return linkService.getAllRoutesIdForRound(roundId);
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


    public void generateStarts(Integer roundId) {
        try {
            tryToGenerateStarts(roundId);
        } catch (Exception e) {
            throw new InternalServerError("Something went wrong while generating starts: '" + e.getMessage() + "'");
        }
    }

    private void tryToGenerateStarts(Integer roundId) {
        Round round = get(roundId);
        List<Route> routes = getRoutes(roundId);
        List<Participant> participants = participantService.getParticipantsByRound(round);

        StartsGenerator generator = new StartsGenerator(round, participants, routes);
        List<StartEntity> startEntities = generator
                .generate()
                .stream()
                .map(s -> mapper.map(s, StartEntity.class))
                .toList();

        startRepo.saveAll(startEntities);
    }
}
