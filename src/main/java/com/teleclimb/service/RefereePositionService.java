package com.teleclimb.service;

import com.teleclimb.controller.responses.error.exception.BadRequestException;
import com.teleclimb.dto.model.RefereePosition;
import com.teleclimb.entitie.RefereePositionEntity;
import com.teleclimb.repository.RefereePositionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public record RefereePositionService(ModelMapper mapper, RefereePositionRepository positionRepo) {

    // --------------------------------- GET ---------------------------------

    public RefereePosition getByRoundIdAndRouteId(Integer roundId, Integer routeId) {
        return mapper.map(positionRepo.findByRoundIdAndRouteId(roundId, routeId).get(0), RefereePosition.class);
    }

    public RefereePosition getByHash(String hash) {
        List<RefereePositionEntity> refereePositions = positionRepo.findByHash(hash);
        if (refereePositions.size() < 1)
            throw new BadRequestException("No referee position with hash \"" + hash + "\"");
        if (refereePositions.size() > 1)
            throw new BadRequestException("There is too much referee positions with hash \"" + hash + "\":" + refereePositions.size());

        return mapper.map(refereePositions.get(0), RefereePosition.class);
    }

    public List<RefereePosition> getAllByRoundId(Integer roundId) {
        return positionRepo.findByRoundId(roundId).stream()
                .map(entity -> mapper.map(entity, RefereePosition.class))
                .toList();
    }

    public List<RefereePosition> getAllByRouteId(Integer routeId) {
        return positionRepo.findByRouteId(routeId).stream()
                .map(entity -> mapper.map(entity, RefereePosition.class))
                .toList();
    }


    // --------------------------------- ADD ---------------------------------

    public void addPosition(Integer roundId, Integer routeId) {
        if (doesPositionExist(roundId, routeId))
            throw new BadRequestException("There is existing position for route id: " + roundId + " and round id: " + roundId);

        RefereePosition position = new RefereePosition();
        position.setRoundId(roundId);
        position.setRouteId(routeId);
        position.setHash(generateUniqueHash());
        positionRepo.save(mapper.map(position, RefereePositionEntity.class));
    }

    private boolean doesPositionExist(Integer roundId, Integer routeId) {
        return positionRepo.findByRoundIdAndRouteId(roundId, routeId).size() != 0;
    }


    private String generateUniqueHash() {
        String newHash;
        int hashLength = 4;
        int counter = 0;
        do {
            newHash = generateNew(hashLength);

            counter++;
            if (counter > 100) {
                hashLength++;
                counter = 0;
            }
        } while (isHashTaken(newHash));
        return newHash;
    }

    private String generateNew(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder hash = new StringBuilder();
        Random rand = new Random();
        while (hash.length() < length) {
            int index = (int) (rand.nextFloat() * chars.length());
            hash.append(chars.charAt(index));
        }
        return hash.toString();
    }

    private Boolean isHashTaken(String hash) {
        if (positionRepo.findByHash(hash).size() == 0) return false;
        return true;
    }

    // --------------------------------- DELETE ---------------------------------

    public void removePosition(Integer roundId, Integer routeId) {
        if (doesPositionExist(roundId, routeId))
            throw new BadRequestException("There is no position to remove, considering route id: " + roundId + " and round id: " + roundId);

        List<RefereePositionEntity> positions = positionRepo.findByRoundIdAndRouteId(roundId, routeId);

        positionRepo.deleteAll(positions);
    }
}
