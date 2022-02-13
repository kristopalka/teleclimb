package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.ContestantDto;
import com.teleclimb.rest.entities.Contestant;
import com.teleclimb.rest.repositories.CompetitionRepository;
import com.teleclimb.rest.repositories.ContestantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record ContestantService(ContestantRepository contestantRepo, CompetitionRepository competitionRepo) {

    public List<ContestantDto> getAll() {
        return contestantRepo.findAll().stream().map(Contestant::toDto).collect(Collectors.toList());
    }

    public ContestantDto get(Long id) {
        return contestantRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found contestant with id: " + id)).toDto();
    }

    public void add(ContestantDto dto) {
        dto.setId(null);
        newDtoValidation(dto);

        contestantRepo.save(dto.toEntity());
    }

    public void update(Long id, ContestantDto newDto) {
        ContestantDto dto = get(id);

        if (newDto.getName() != null) dto.setName(newDto.getName());
        if (newDto.getLastName() != null) dto.setLastName(newDto.getLastName());
        if (newDto.getStartNumber() != null) dto.setStartNumber(newDto.getStartNumber());
        if (newDto.getClubName() != null) dto.setClubName(newDto.getClubName());
        if (newDto.getBirthDate() != null) dto.setBirthDate(newDto.getBirthDate());

        contestantRepo.save(dto.toEntity());
    }

    public void delete(Long id) {
        //todo remove all starts (use @transactional?)
        contestantRepo.deleteById(id);
    }


    private void newDtoValidation(ContestantDto dto) {
        if (dto.getCompetition() == null) throw new BadRequestException("Competition cannot be null");

        if (!competitionRepo.existsById(dto.getCompetition().getId()))
            throw new BadRequestException("Competition with specific id does not exist");
    }
}
