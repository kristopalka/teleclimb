package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.responses.error.exception.NotImplementedException;
import com.teleclimb.rest.dto.RoundDto;
import com.teleclimb.rest.dto.RouteDto;
import com.teleclimb.rest.entities.Round;
import com.teleclimb.rest.repositories.RoundRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record RoundService(ModelMapper mapper, RoundRepository roundRepo, RoundRouteLinkService linkService) {

    public List<RoundDto> getAll() {
        return roundRepo.findAll()
                .stream()
                .map(r -> mapper.map(r, RoundDto.class))
                .collect(Collectors.toList());
    }

    public RoundDto get(Long id) {
        Round round = roundRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found round with id: " + id));

        return mapper.map(round, RoundDto.class);
    }

    public List<RouteDto> getRoutes(Long roundId) {
        return linkService.getAllRoutesIdForRound(roundId);
    }

    public void linkRoute(Long id, Long routeId) {
        RoundDto round = get(id);

        if (getRoutes(id).size() >= round.getNumberOfRoutes())
            throw new BadRequestException("There is max number of links to round with id: " + id);

        linkService.addLink(id, routeId);
    }

    public void unlinkRoute(Long id, Long routeId) {
        linkService.removeLink(id, routeId);
    }


    public void generateStarts(Long id) {
        throw new NotImplementedException("not implemented yet");
    }
}
