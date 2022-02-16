package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.rest.dto.Route;
import com.teleclimb.rest.entities.RoundEntity;
import com.teleclimb.rest.entities.RoundRouteLinkEntity;
import com.teleclimb.rest.entities.RouteEntity;
import com.teleclimb.rest.repositories.RoundRouteLinkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoundRouteLinkService(ModelMapper mapper, RoundRouteLinkRepository linkRepo, RoundService roundService) {
    public List<Route> getAllRoutesForRoundId(Integer roundId) {
        List<RoundRouteLinkEntity> links = linkRepo.findByRoundId(roundId);

        return links.stream()
                .map(RoundRouteLinkEntity::getRoute)
                .map(r -> mapper.map(r, Route.class))
                .toList();
    }

    public void addLink(Integer roundId, Integer routeId) {
        if (getAllRoutesForRoundId(roundId).size() >= roundService.get(roundId).getNumberOfRoutes())
            throw new BadRequestException("There is max number of links to round with id: " + routeId);

        if (linkRepo.findByRoundIdAndRouteId(roundId, routeId).size() != 0)
            throw new BadRequestException("There is existing link between route id: " + roundId + " and round id: " + roundId);

        RoundEntity roundEntity = new RoundEntity();
        roundEntity.setId(roundId);

        RouteEntity routeEntity = new RouteEntity();
        routeEntity.setId(routeId);

        RoundRouteLinkEntity link = new RoundRouteLinkEntity();
        link.setRound(roundEntity);
        link.setRoute(routeEntity);
        linkRepo.save(link);
    }


    public void removeLink(Integer roundId, Integer routeId) {
        if (linkRepo.findByRoundIdAndRouteId(roundId, routeId).size() == 0)
            throw new BadRequestException("There is no link to remove, between routeEntity id: " + roundId + " and roundEntity id: " + roundId);

        RoundEntity roundEntity = new RoundEntity();
        roundEntity.setId(roundId);

        RouteEntity routeEntity = new RouteEntity();
        routeEntity.setId(routeId);

        List<RoundRouteLinkEntity> links = linkRepo.findByRoundIdAndRouteId(roundId, routeId);

        linkRepo.deleteAll(links);
    }


}
