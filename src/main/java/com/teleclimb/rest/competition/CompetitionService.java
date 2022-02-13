package com.teleclimb.rest.competition;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.contestant.ContestantEntity;
import com.teleclimb.rest.contestant.ContestantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository repo;
    private final ContestantRepository contestantRepo;

    public List<CompetitionEntity> getAll() {
        return repo.findAll();
    }

    public CompetitionEntity get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found competition with id: " + id));
    }

    public void add(CompetitionEntity competition) {
        repo.save(competition);
    }

    public void update(Long id, CompetitionEntity competition) {
        repo.findById(id)
                .map(c -> {
                    c.setName(competition.getName());
                    c.setCategory(competition.getCategory());
                    c.setCompetitionType(competition.getCompetitionType());
                    c.setGender(competition.getGender());
                    return repo.save(c);
                })
                .orElseThrow(() -> new NotFoundException("Not found competition with id: " + id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }



    public List<ContestantEntity> getAllContestantForCompetition(Long competitionId) {
        CompetitionEntity competition = new CompetitionEntity();
        competition.setId(competitionId);

        return contestantRepo.findByCompetitionId(competition);
    }
}
