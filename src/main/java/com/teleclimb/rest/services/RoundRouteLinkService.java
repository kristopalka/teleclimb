package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.rest.dto.RouteDto;
import com.teleclimb.rest.entities.Round;
import com.teleclimb.rest.entities.RoundRouteLink;
import com.teleclimb.rest.entities.Route;
import com.teleclimb.rest.repositories.RoundRouteLinkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoundRouteLinkService(ModelMapper mapper, RoundRouteLinkRepository linkRepo) {
    public List<RouteDto> getAllRoutesIdForRound(Long roundId) {
        List<RoundRouteLink> links = linkRepo.findByRoundId(roundId);

        return links.stream()
                .map(RoundRouteLink::getRoute)
                .map(r -> mapper.map(r, RouteDto.class))
                .toList();
    }

    public void addLink(Long roundId, Long routeId) {
        if (linkRepo.findByRoundIdAndRouteId(roundId, routeId).size() != 0)
            throw new BadRequestException("There is existing link between route id: " + roundId + " and round id: " + roundId);

        Round round = new Round();
        round.setId(roundId);

        Route route = new Route();
        route.setId(routeId);

        RoundRouteLink link = new RoundRouteLink();
        link.setRound(round);
        link.setRoute(route);
        linkRepo.save(link);
    }


    public void removeLink(Long roundId, Long routeId) {
        if (linkRepo.findByRoundIdAndRouteId(roundId, routeId).size() == 0)
            throw new BadRequestException("There is no link to remove, between route id: " + roundId + " and round id: " + roundId);

        Round round = new Round();
        round.setId(roundId);

        Route route = new Route();
        route.setId(routeId);

        List<RoundRouteLink> links = linkRepo.findByRoundIdAndRouteId(roundId, routeId);

        linkRepo.deleteAll(links);
    }


}
