package com.teleclimb.service;

import com.teleclimb.controller.responses.error.exception.BadRequestException;
import com.teleclimb.controller.responses.error.exception.NotFoundException;
import com.teleclimb.dto.model.Meta;
import com.teleclimb.dto.model.Participant;
import com.teleclimb.dto.model.ParticipantWithMeta;
import com.teleclimb.dto.model.Round;
import com.teleclimb.entitie.ParticipantEntity;
import com.teleclimb.repository.ParticipantRepository;
import com.teleclimb.service.round.RoundService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record ParticipantService(ModelMapper mapper, ParticipantRepository participantRepo, RoundService roundService,
                                 ParticipantMetaService metaService) {

    // --------------------------------- GET ---------------------------------

    public Participant get(Integer id) {
        ParticipantEntity participantEntity = participantRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found participant with id: " + id));

        return mapper.map(participantEntity, Participant.class);
    }

    public List<Participant> getAll() {
        return participantRepo.findAll().stream().map(entity -> mapper.map(entity, Participant.class)).toList();
    }

    public List<Participant> getAllByCompetitionId(Integer competitionId) {
        return participantRepo.findByCompetitionId(competitionId).stream().map(entity -> mapper.map(entity, Participant.class)).toList();
    }

    public List<ParticipantWithMeta> getAllParticipantsWithMetaByCompetitionId(Integer competitionId) {
        List<ParticipantWithMeta> participants = participantRepo.
                findByCompetitionId(competitionId).stream().map(entity -> mapper.map(entity, ParticipantWithMeta.class)).toList();

        for (ParticipantWithMeta participant : participants) {
            List<Meta> immutableMeta = metaService.getAllByParticipantId(participant.getId());
            participant.setMeta(new ArrayList<>(immutableMeta));
        }
        return participants;
    }

    public List<Participant> getParticipantsByRoundId(Integer roundId) {
        Round round = roundService.get(roundId);
        List<ParticipantEntity> participantEntities = participantRepo.findByCompetitionIdAndTopRoundNumber(round.getCompetitionId(), round.getSequenceNumber());
        return participantEntities.stream().map(entity -> mapper.map(entity, Participant.class)).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public Participant add(Participant newParticipant) {
        newParticipant.setTopRoundNumber(0);
        newParticipant.setPlace(null);
        if (newParticipant.getRankingPosition() == null) newParticipant.setRankingPosition(Integer.MAX_VALUE);

        validateParticipant(newParticipant);

        ParticipantEntity participantEntity = participantRepo.save(mapper.map(newParticipant, ParticipantEntity.class));
        return mapper.map(participantEntity, Participant.class);
    }

    private void validateParticipant(Participant participant) {
        if (participant.getCompetitionId() == null) throw new BadRequestException("Competition id cannot be null");
    }


    // --------------------------------- UPDATE ---------------------------------

    public Participant update(Integer participantId, Participant newParticipant) {
        Participant dto = get(participantId);

        if (newParticipant.getName() != null) dto.setName(newParticipant.getName());
        if (newParticipant.getLastName() != null) dto.setLastName(newParticipant.getLastName());
        if (newParticipant.getRankingPosition() != null) dto.setRankingPosition(newParticipant.getRankingPosition());
        if (newParticipant.getStartNumber() != null) dto.setStartNumber(newParticipant.getStartNumber());
        if (newParticipant.getClubName() != null) dto.setClubName(newParticipant.getClubName());
        if (newParticipant.getBirthDate() != null) dto.setBirthDate(newParticipant.getBirthDate());

        ParticipantEntity participantEntity = participantRepo.save(mapper.map(dto, ParticipantEntity.class));
        return mapper.map(participantEntity, Participant.class);
    }

    public void incrementTopRoundSequenceNumber(Integer participantId) {
        Participant participant = get(participantId);
        participant.setTopRoundNumber(participant.getTopRoundNumber() + 1);
        participantRepo.save(mapper.map(participant, ParticipantEntity.class));
    }

    public void updateDataAndMetaForAll(List<ParticipantWithMeta> participants) {
        for (ParticipantWithMeta participant : participants) {
            participantRepo.save(mapper.map(participant, ParticipantEntity.class));

            for (Meta meta : participant.getMeta()) {
                metaService.addOrUpdate(participant.getId(), meta.getKey(), meta.getValue());
            }
        }
    }


    // --------------------------------- DELETE ---------------------------------

    public void delete(Integer id) {
        //todo remove all starts (use @transactional?)
        participantRepo.deleteById(id);
    }

    public void deleteAllByCompetitionId(Integer competitionId) {
        getAllByCompetitionId(competitionId).forEach(p -> delete(p.getId()));
    }
}

//todo gdzie przenieść tą metodę? (tu nie może być bo nie można podpiąć competitionService)
//
//    public void updateRoundSequenceNumber(Integer participantId, Integer newRoundSequenceNumber) {
//        Participant participant = get(participantId);
//        Competition competition = competitionService.get(participant.getCompetitionId());
//        Formula formula = formulaService.get(competition.getFormulaId());
//
//        if (newRoundSequenceNumber < 0 || newRoundSequenceNumber >= formula.getNumberOfRounds())
//            throw new RuntimeException("New round sequence number is out of range possible values");
//
//        participant.setRoundSequenceNumber(newRoundSequenceNumber);
//        participantRepo.save(toEntity(participant));
//    }