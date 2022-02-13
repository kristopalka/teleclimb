package com.teleclimb.rest.competition;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.contestant.ContestantEntity;
import com.teleclimb.rest.contestant.ContestantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository competitionRepo;
    private final ContestantRepository contestantRepo;

    public List<CompetitionDto> getAll() {
        return competitionRepo.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public CompetitionDto get(Long id) {
        return entityToDto(competitionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found competition with id: " + id)));
    }

    public void add(CompetitionDto dto) {
        newDtoValidation(dto);
        competitionRepo.save(dtoToEntity(dto));
    }

    public void update(Long id, CompetitionDto newDto) {
        CompetitionDto dto = get(id);

        if(newDto.getName() != null) dto.setName(newDto.getName());

        competitionRepo.save(dtoToEntity(dto));
    }

    public void delete(Long id) {
        competitionRepo.deleteById(id);
    }

    public List<ContestantEntity> getAllContestantForCompetition(Long competitionId) {
        Competition competition = new Competition();
        competition.setId(competitionId);

        return contestantRepo.findByCompetitionId(competition);
    }



    private void newDtoValidation(CompetitionDto dto) {
        if(dto.getName() == null) throw new BadRequestException("Name cannot be null");
        if(dto.getCompetitionType() == null) throw new BadRequestException("CompetitionType cannot be null");
        if(dto.getGender() == null) throw new BadRequestException("Gender cannot be null");
        if(dto.getCategory() == null) throw new BadRequestException("Category cannot be null");
    }

    private CompetitionDto entityToDto(Competition competition) {
        CompetitionDto dto = new CompetitionDto();

        dto.setId(competition.getId());
        dto.setName(competition.getName());
        dto.setGender(competition.getGender());
        dto.setCategory(competition.getCategory());
        dto.setCompetitionType(competition.getCompetitionType());

        return dto;
    }

    private Competition dtoToEntity(CompetitionDto dto) {
        Competition competition = new Competition();

        competition.setId(dto.getId());
        competition.setName(dto.getName());
        competition.setGender(dto.getGender());
        competition.setCategory(dto.getCategory());
        competition.setCompetitionType(dto.getCompetitionType());

        return competition;
    }
}
