package com.teleclimb.rest.services;

import com.teleclimb.rest.dto.Competition;
import com.teleclimb.rest.entities.CompetitionEntity;
import com.teleclimb.rest.repositories.CompetitionRepository;
import com.teleclimb.rest.responses.error.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CompetitionService(ModelMapper mapper, CompetitionRepository competitionRepo,
                                 ValidationService validationService,
                                 ParticipantService participantService, RoundService roundService) {

    // --------------------------------- GET ---------------------------------

    public Competition get(Integer id) {
        validationService.validateCompetitionId(id);
        CompetitionEntity competitionEntity = competitionRepo.findById(id).orElseThrow();
        return toDto(competitionEntity);
    }

    public List<Competition> getAll() {
        return competitionRepo.findAll().stream().map(this::toDto).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public Competition add(Competition competition) {
        competition.setId(null);
        validateCompetition(competition);

        return toDto(competitionRepo.save(toEntity(competition)));
    }

    private void validateCompetition(Competition competition) {
        if (competition.getName() == null) throw new BadRequestException("Name cannot be null");
        if (competition.getFormulaId() == null) throw new BadRequestException("Formula id cannot be null");
        if (competition.getGender() == null) throw new BadRequestException("Gender cannot be null");
        if (competition.getCategoryId() == null) throw new BadRequestException("Category id cannot be null");

        validationService.validateCategoryId(competition.getCategoryId());
        validationService.validateFormulaId(competition.getFormulaId());
    }


    // --------------------------------- UPDATE ---------------------------------

    public Competition update(Integer id, Competition newCompetition) {
        Competition competition = get(id);

        if (newCompetition.getName() != null) competition.setName(newCompetition.getName());

        return toDto(competitionRepo.save(toEntity(competition)));
    }


    // --------------------------------- DELETE ---------------------------------

    public void delete(Integer id) {
        participantService.deleteAllByCompetitionId(id);
        roundService.deleteAllByCompetitionId(id);

        competitionRepo.deleteById(id);
    }


    // --------------------------------- MAPPING ---------------------------------

    private Competition toDto(CompetitionEntity entity) {
        return mapper.map(entity, Competition.class);
    }

    private CompetitionEntity toEntity(Competition dto) {
        return mapper.map(dto, CompetitionEntity.class);
    }

}
