package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.Competition;
import com.teleclimb.rest.dto.Formula;
import com.teleclimb.rest.dto.Participant;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.entities.ParticipantEntity;
import com.teleclimb.rest.repositories.ParticipantRepository;
import com.teleclimb.rest.responses.error.exception.BadRequestException;
import com.teleclimb.rest.responses.error.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record ParticipantService(ModelMapper mapper, ParticipantRepository participantRepo,
                                 CompetitionService competitionService, FormulaService formulaService,
                                 RoundService roundService) {

    public List<Participant> getAll() {
        return participantRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Participant> getAllByCompetitionId(Integer competitionId) {
        return participantRepo.findByCompetitionId(competitionId)
                .stream()
                .map(p -> mapper.map(p, Participant.class))
                .toList();
    }

    public List<Participant> getParticipantsByRoundId(Integer roundId) {
        Round round = roundService.get(roundId);
        return participantRepo.findByCompetitionIdAndRoundSequenceNumber(round.getCompetitionId(), round.getSequenceNumber())
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Participant get(Integer id) {
        ParticipantEntity participantEntity = participantRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found participant with id: " + id));

        return toDto(participantEntity);
    }

    public Participant add(Participant participant) {
        participant.setId(null);
        participant.setRoundSequenceNumber(0);
        if (participant.getRankingPosition() == null) participant.setRankingPosition(Integer.MAX_VALUE);
        newParticipantValidation(participant);

        ParticipantEntity participantEntity = participantRepo.save(toEntity(participant));
        return toDto(participantEntity);
    }

    public Participant update(Integer id, Participant newParticipant) {
        Participant dto = get(id);

        if (newParticipant.getName() != null) dto.setName(newParticipant.getName());
        if (newParticipant.getLastName() != null) dto.setLastName(newParticipant.getLastName());
        if (newParticipant.getRankingPosition() != null) dto.setRankingPosition(newParticipant.getRankingPosition());
        if (newParticipant.getStartNumber() != null) dto.setStartNumber(newParticipant.getStartNumber());
        if (newParticipant.getClubName() != null) dto.setClubName(newParticipant.getClubName());
        if (newParticipant.getBirthDate() != null) dto.setBirthDate(newParticipant.getBirthDate());

        ParticipantEntity participantEntity = participantRepo.save(toEntity(dto));
        return toDto(participantEntity);
    }

    public void updateRoundSequenceNumber(Integer participantId, Integer newRoundSequenceNumber) {
        Participant participant = get(participantId);
        Competition competition = competitionService.get(participant.getCompetitionId());
        Formula formula = formulaService.get(competition.getFormulaId());

        if (newRoundSequenceNumber < 0 || newRoundSequenceNumber >= formula.getNumberOfRounds())
            throw new RuntimeException("New round sequence number is out of range possible values");

        participant.setRoundSequenceNumber(newRoundSequenceNumber);
        participantRepo.save(toEntity(participant));
    }

    public void delete(Integer id) {
        //todo remove all starts (use @transactional?)
        participantRepo.deleteById(id);
    }


    private void newParticipantValidation(Participant participant) {
        if (participant.getCompetitionId() == null) throw new BadRequestException("Competition id cannot be null");

        if (competitionService.get(participant.getCompetitionId()) == null)
            throw new BadRequestException("Competition with specific id does not exist");
    }

    private Participant toDto(ParticipantEntity entity) {
        return mapper.map(entity, Participant.class);
    }

    private ParticipantEntity toEntity(Participant dto) {
        return mapper.map(dto, ParticipantEntity.class);
    }
}
