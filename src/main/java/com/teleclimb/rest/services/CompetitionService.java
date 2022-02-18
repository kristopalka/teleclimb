package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.Competition;
import com.teleclimb.rest.entities.CompetitionEntity;
import com.teleclimb.rest.repositories.CompetitionRepository;
import com.teleclimb.rest.responses.error.exception.BadRequestException;
import com.teleclimb.rest.responses.error.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record CompetitionService(ModelMapper mapper, CompetitionRepository competitionRepo,
                                 CategoryService categoryService, FormulaService formulaService) {

    public List<Competition> getAll() {
        return competitionRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Competition get(Integer id) {
        CompetitionEntity competitionEntity = competitionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found competition with id: " + id));

        return toDto(competitionEntity);
    }

    public Competition add(Competition competition) {
        competition.setId(null);
        newCompetitionValidation(competition);

        return toDto(competitionRepo.save(toEntity(competition)));
    }

    public Competition update(Integer id, Competition newCompetition) {
        Competition competition = get(id);

        if (newCompetition.getName() != null) competition.setName(newCompetition.getName());

        return toDto(competitionRepo.save(toEntity(competition)));
    }

    public void delete(Integer id) {
        //todo remove all belonging fields (contestants and rounds)
        competitionRepo.deleteById(id);
    }


    private void newCompetitionValidation(Competition competition) {
        if (competition.getName() == null) throw new BadRequestException("Name cannot be null");
        if (competition.getFormulaId() == null) throw new BadRequestException("Formula id cannot be null");
        if (competition.getGender() == null) throw new BadRequestException("Gender cannot be null");
        if (competition.getCategoryId() == null) throw new BadRequestException("Category id cannot be null");

        if (categoryService.get(competition.getCategoryId()) == null)
            throw new BadRequestException("Category with specific id does not exist");

        if (formulaService.get(competition.getFormulaId()) == null)
            throw new BadRequestException("Formula with specific id does not exist");
    }

    private Competition toDto(CompetitionEntity entity) {
        return mapper.map(entity, Competition.class);
    }

    private CompetitionEntity toEntity(Competition dto) {
        return mapper.map(dto, CompetitionEntity.class);
    }
}
