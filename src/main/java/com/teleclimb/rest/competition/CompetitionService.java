package com.teleclimb.rest.competition;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository competitionRepo;
    private final CategoryRepository categoryRepo;


    public List<CompetitionDto> getAll() {
        return competitionRepo.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public CompetitionDto get(Long id) {
        return entityToDto(competitionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found competition with id: " + id)));
    }

    public void add(CompetitionDto dto) {
        dto.setId(null);
        newDtoValidation(dto);
        competitionRepo.save(dtoToEntity(dto));
    }

    public void update(Long id, CompetitionDto newDto) {
        CompetitionDto dto = get(id);

        if(newDto.getName() != null) dto.setName(newDto.getName());

        competitionRepo.save(dtoToEntity(dto));
    }

    public void delete(Long id) {
        //todo remove all belonging fields (contestants and rounds)
        competitionRepo.deleteById(id);
    }


    private void newDtoValidation(CompetitionDto dto) {
        if(dto.getName() == null) throw new BadRequestException("Name cannot be null");
        if(dto.getCompetitionType() == null) throw new BadRequestException("CompetitionType cannot be null");
        if(dto.getGender() == null) throw new BadRequestException("Gender cannot be null");
        if(dto.getCategory() == null) throw new BadRequestException("Category cannot be null");

        if(!categoryRepo.existsById(dto.getCategory().getId())) throw new BadRequestException("Category with specific id does not exist");
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
