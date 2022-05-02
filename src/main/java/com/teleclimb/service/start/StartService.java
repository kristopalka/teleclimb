package com.teleclimb.service.start;

import com.teleclimb.controller.responses.error.exception.BadRequestException;
import com.teleclimb.controller.responses.error.exception.NotFoundException;
import com.teleclimb.dto.enums.RoundState;
import com.teleclimb.dto.model.RefereePosition;
import com.teleclimb.dto.model.Start;
import com.teleclimb.entitie.StartEntity;
import com.teleclimb.repository.StartRepository;
import com.teleclimb.service.RefereePositionService;
import com.teleclimb.util.ScoreChecker;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record StartService(ModelMapper mapper, StartRepository startRepo, RefereePositionService positionService) {

    // --------------------------------- GET ---------------------------------

    public Start get(Integer id) {
        StartEntity startEntity = startRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found start with id: " + id));

        return mapper.map(startEntity, Start.class);
    }

    public Start getByRefereePositionIdAndParticipantId(Integer positionId, Integer participantId) {
        return mapper.map(startRepo.findByRefereePositionIdAndParticipantId(positionId, participantId).get(0), Start.class);
    }

    public List<Start> getAll() {
        return startRepo.findAll().stream().map(entity -> mapper.map(entity, Start.class)).toList();
    }

    public List<Start> getAllByRefereePositionId(Integer positionId) {
        return startRepo.findByRefereePositionId(positionId).stream().map(entity -> mapper.map(entity, Start.class)).toList();
    }

    public List<Start> getAllByRoundId(Integer positionId) {
        List<RefereePosition> positions = positionService.getAllByRoundId(positionId);

        List<Start> starts = new ArrayList<>();
        for (RefereePosition position : positions) {
            starts.addAll(getAllByRefereePositionId(position.getId()));
        }
        return starts;
    }

    public List<Start> getAllByParticipantId(Integer participantId) {
        return startRepo.findByParticipantId(participantId).stream().map(entity -> mapper.map(entity, Start.class)).toList();
    }

    public List<Start> getByRefereePositionHash(String hash) {
        hash = hash.toUpperCase();
        Integer positionId = positionService.getByHash(hash).getId();
        return startRepo.findByRefereePositionId(positionId).stream().map(entity -> mapper.map(entity, Start.class)).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public Start add(Start start) {
        return mapper.map(startRepo.save(mapper.map(start, StartEntity.class)), Start.class);
    }

    public void addAll(List<Start> starts) {
        starts.forEach(this::add);
    }


    // --------------------------------- UPDATE ---------------------------------

    public Start updateSequenceNumber(Integer startId, Integer sequenceNumber) {
        Start start = get(startId);

        start.setPositionSequenceNumber(sequenceNumber);

        StartEntity startEntity = startRepo.save(mapper.map(start, StartEntity.class));
        return mapper.map(startEntity, Start.class);
    }

    public Start updateScoreMobileApp(Integer startId, String score, String hash) {
        Start start = get(startId);

        if (!hash.equals(start.getRefereePositionHash()))
            throw new BadRequestException("Bad hash code, you can not update this score");
        return updateScore(startId, score);
    }

    public Start updateScore(Integer startId, String score) {
        Start start = get(startId);

        if (start.getRoundState() != RoundState.IN_PROGRESS)
            throw new BadRequestException("Round is not in progress. Can not update score");
        ScoreChecker.check(score, start.getDiscipline());

        start.setScore(score);

        StartEntity startEntity = startRepo.save(mapper.map(start, StartEntity.class));
        return mapper.map(startEntity, Start.class);
    }


}
