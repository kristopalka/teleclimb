package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.RouteDto;
import com.teleclimb.rest.entities.RoundRouteLink;
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

    }
}
