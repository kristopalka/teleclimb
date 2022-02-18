package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.RoundRouteLink;
import com.teleclimb.rest.entities.RoundRouteLinkEntity;
import com.teleclimb.rest.repositories.RoundRouteLinkRepository;
import com.teleclimb.rest.responses.error.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoundRouteLinkService(ModelMapper mapper, RoundRouteLinkRepository linkRepo, RoundService roundService) {

    // --------------------------------- GET ---------------------------------

    public List<RoundRouteLink> getAllLinksByRoundId(Integer roundId) {
        return linkRepo.findByRoundId(roundId).stream().map(this::toDto).toList();
    }

    public List<RoundRouteLink> getAllLinksByRouteId(Integer routeId) {
        return linkRepo.findByRouteId(routeId).stream().map(this::toDto).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public void addLink(Integer roundId, Integer routeId) {
        if (doesLinkExist(roundId, routeId))
            throw new BadRequestException("There is existing link between route id: " + roundId + " and round id: " + roundId);

        RoundRouteLink link = new RoundRouteLink();
        link.setRoundId(roundId);
        link.setRouteId(routeId);
        linkRepo.save(toEntity(link));
    }

    private boolean doesLinkExist(Integer roundId, Integer routeId) {
        return linkRepo.findByRoundIdAndRouteId(roundId, routeId).size() != 0;
    }


    // --------------------------------- DELETE ---------------------------------

    public void removeLink(Integer roundId, Integer routeId) {
        if (doesLinkExist(roundId, routeId))
            throw new BadRequestException("There is no link to remove, between routeEntity id: " + roundId + " and roundEntity id: " + roundId);

        List<RoundRouteLinkEntity> links = linkRepo.findByRoundIdAndRouteId(roundId, routeId);

        linkRepo.deleteAll(links);
    }


    // --------------------------------- MAPPING ---------------------------------

    private RoundRouteLink toDto(RoundRouteLinkEntity entity) {
        return mapper.map(entity, RoundRouteLink.class);
    }

    private RoundRouteLinkEntity toEntity(RoundRouteLink dto) {
        return mapper.map(dto, RoundRouteLinkEntity.class);
    }
}
