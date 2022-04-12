package com.teleclimb.services;

import com.teleclimb.controllers.responses.error.exception.BadRequestException;
import com.teleclimb.controllers.responses.error.exception.NotFoundException;
import com.teleclimb.dto.Competition;
import com.teleclimb.entities.CompetitionEntity;
import com.teleclimb.repositories.CompetitionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CompetitionService(ModelMapper mapper, CompetitionRepository competitionRepo,
                                 ParticipantService participantService, RoundService roundService) {

    // --------------------------------- GET ---------------------------------

    public Competition get(Integer id) {
        CompetitionEntity competitionEntity = competitionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found competition with: " + id));

        return mapper.map(competitionEntity, Competition.class);
    }

    public List<Competition> getAll() {
        return competitionRepo.findAll().stream().map(entity -> mapper.map(entity, Competition.class)).toList();
    }


    // --------------------------------- ADD ---------------------------------

    public Competition add(Competition competition) {
        competition.setId(null);
        validateCompetition(competition);

        CompetitionEntity competitionEntity = competitionRepo.save(mapper.map(competition, CompetitionEntity.class));
        return mapper.map(competitionEntity, Competition.class);
    }

    private void validateCompetition(Competition competition) {
        if (competition.getName() == null) throw new BadRequestException("Name cannot be null");
        if (competition.getFormulaId() == null) throw new BadRequestException("Formula id cannot be null");
        if (competition.getGender() == null) throw new BadRequestException("Gender cannot be null");
        if (competition.getCategoryId() == null) throw new BadRequestException("Category id cannot be null");
    }


    // --------------------------------- UPDATE ---------------------------------

    public Competition update(Integer id, Competition newCompetition) {
        Competition competition = get(id);

        if (newCompetition.getName() != null) competition.setName(newCompetition.getName());

        CompetitionEntity competitionEntity = competitionRepo.save(mapper.map(competition, CompetitionEntity.class));
        return mapper.map(competitionEntity, Competition.class);
    }


    // --------------------------------- DELETE ---------------------------------

    public void delete(Integer id) {
        participantService.deleteAllByCompetitionId(id);
        roundService.deleteAllByCompetitionId(id);

        competitionRepo.deleteById(id);
    }
}
