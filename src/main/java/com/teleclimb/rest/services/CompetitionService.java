package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exception.BadRequestException;
import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.dto.CompetitionDto;
import com.teleclimb.rest.entities.Competition;
import com.teleclimb.rest.repositories.CategoryRepository;
import com.teleclimb.rest.repositories.CompetitionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record CompetitionService(ModelMapper mapper, CompetitionRepository competitionRepo, CategoryRepository categoryRepo) {

    public List<CompetitionDto> getAll() {
        return competitionRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CompetitionDto get(Long id) {
        Competition competition = competitionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found competition with id: " + id));

        return toDto(competition);
    }

    public CompetitionDto add(CompetitionDto dto) {
        dto.setId(null);
        newDtoValidation(dto);

        Competition competition = competitionRepo.save(toEntity(dto));
        return toDto(competition);
    }

    public CompetitionDto update(Long id, CompetitionDto newDto) {
        CompetitionDto dto = get(id);

        if (newDto.getName() != null) dto.setName(newDto.getName());

        Competition competition = competitionRepo.save(toEntity(dto));
        return toDto(competition);
    }

    public void delete(Long id) {
        //todo remove all belonging fields (contestants and rounds)
        competitionRepo.deleteById(id);
    }


    private void newDtoValidation(CompetitionDto dto) {
        if (dto.getName() == null) throw new BadRequestException("Name cannot be null");
        if (dto.getFormula() == null) throw new BadRequestException("Formula cannot be null");
        if (dto.getGender() == null) throw new BadRequestException("Gender cannot be null");
        if (dto.getCategory() == null) throw new BadRequestException("Category cannot be null");

        if (!categoryRepo.existsById(dto.getCategory().getId()))
            throw new BadRequestException("Category with specific id does not exist");
    }

    private CompetitionDto toDto(Competition entity) {
        return mapper.map(entity, CompetitionDto.class);
    }

    private Competition toEntity(CompetitionDto dto) {
        return mapper.map(dto, Competition.class);
    }
}
