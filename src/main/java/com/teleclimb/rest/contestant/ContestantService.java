package com.teleclimb.rest.contestant;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.competition.CompetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContestantService {
    private final ContestantRepository contestantRepo;
    private final CompetitionRepository competitionRepo;


    public List<ContestantDto> getAll() {
        return contestantRepo.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public ContestantDto get(Long id) {
        return entityToDto(contestantRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found contestant with id: " + id)));
    }

    public void add(ContestantDto dto) {
        dto.setId(null);
        newDtoValidation(dto);

        contestantRepo.save(dtoToEntity(dto));
    }

    public void update(Long id, ContestantDto newDto) {
        ContestantDto dto = get(id);

        if(newDto.getName() != null) dto.setName(newDto.getName());
        if(newDto.getLastName() != null) dto.setLastName(newDto.getLastName());
        if(newDto.getStartNumber() != null) dto.setStartNumber(newDto.getStartNumber());
        if(newDto.getClubName() != null) dto.setClubName(newDto.getClubName());
        if(newDto.getBirthDate() != null) dto.setBirthDate(newDto.getBirthDate());

        contestantRepo.save(dtoToEntity(dto));
    }

    public void delete(Long id) {
        //todo remove all starts (use @transactional?)
        contestantRepo.deleteById(id);
    }

    public List<ContestantDto> getAllContestantForCompetition(Long competitionId) {
        return contestantRepo.findByCompetitionId(competitionId).stream().map(this::entityToDto).collect(Collectors.toList());
    }


    
    private void newDtoValidation(ContestantDto dto) {
        if (dto.getCompetition() == null) throw new BadRequestException("Competition cannot be null");

        if (!competitionRepo.existsById(dto.getCompetition().getId()))
            throw new BadRequestException("Competition with specific id does not exist");
    }

    private ContestantDto entityToDto(Contestant contestant) {
        ContestantDto dto = new ContestantDto();

        dto.setId(contestant.getId());
        dto.setCompetition(contestant.getCompetition());
        dto.setName(contestant.getName());
        dto.setLastName(contestant.getLastName());
        dto.setStartNumber(contestant.getStartNumber());
        dto.setClubName(contestant.getClubName());
        dto.setBirthDate(contestant.getBirthDate());

        return dto;
    }

    private Contestant dtoToEntity(ContestantDto dto) {
        Contestant competition = new Contestant();

        competition.setId(dto.getId());
        competition.setCompetition(dto.getCompetition());
        competition.setName(dto.getName());
        competition.setLastName(dto.getLastName());
        competition.setStartNumber(dto.getStartNumber());
        competition.setClubName(dto.getClubName());
        competition.setBirthDate(dto.getBirthDate());

        return competition;
    }
}
