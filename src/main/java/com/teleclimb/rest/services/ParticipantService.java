package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.ParticipantDto;
import com.teleclimb.rest.entities.Participant;
import com.teleclimb.rest.repositories.CompetitionRepository;
import com.teleclimb.rest.repositories.ParticipantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record ParticipantService(ModelMapper mapper, ParticipantRepository participantRepo, CompetitionRepository competitionRepo) {

    public List<ParticipantDto> getAll() {
        return participantRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ParticipantDto get(Long id) {
        Participant participant = participantRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found participant with id: " + id));

        return toDto(participant);
    }

    public ParticipantDto add(ParticipantDto dto) {
        dto.setId(null);
        dto.setRoundSequenceNumber(0);
        newDtoValidation(dto);

        Participant participant = participantRepo.save(toEntity(dto));
        return toDto(participant);
    }

    public ParticipantDto update(Long id, ParticipantDto newDto) {
        ParticipantDto dto = get(id);

        if (newDto.getName() != null) dto.setName(newDto.getName());
        if (newDto.getLastName() != null) dto.setLastName(newDto.getLastName());
        if (newDto.getStartNumber() != null) dto.setStartNumber(newDto.getStartNumber());
        if (newDto.getClubName() != null) dto.setClubName(newDto.getClubName());
        if (newDto.getBirthDate() != null) dto.setBirthDate(newDto.getBirthDate());

        Participant participant = participantRepo.save(toEntity(dto));
        return toDto(participant);
    }

    public void updateRoundSequenceNumber(Long participantId, Integer newRoundSequenceNumber) {
        ParticipantDto dto = get(participantId);

        if (newRoundSequenceNumber < 0 || newRoundSequenceNumber >= dto.getCompetition().getFormula().getNumberOfRounds())
            throw new RuntimeException("New round sequence number is out of range possible values");

        dto.setRoundSequenceNumber(newRoundSequenceNumber);
        participantRepo.save(toEntity(dto));
    }

    public void delete(Long id) {
        //todo remove all starts (use @transactional?)
        participantRepo.deleteById(id);
    }


    private void newDtoValidation(ParticipantDto dto) {
        if (dto.getCompetition() == null) throw new BadRequestException("Competition cannot be null");

        if (!competitionRepo.existsById(dto.getCompetition().getId()))
            throw new BadRequestException("Competition with specific id does not exist");
    }

    private ParticipantDto toDto(Participant entity) {
        return mapper.map(entity, ParticipantDto.class);
    }

    private Participant toEntity(ParticipantDto dto) {
        return mapper.map(dto, Participant.class);
    }
}
