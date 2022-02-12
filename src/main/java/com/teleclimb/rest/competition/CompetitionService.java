package com.teleclimb.rest.competition;

import com.teleclimb.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository repo;

    public List<CompetitionEntity> getAll() {
        return repo.findAll();
    }

    public CompetitionEntity get(Long id) {
        return repo.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public void add(CompetitionEntity competition) {
        repo.save(competition);
    }

    public void update(Long id, CompetitionEntity competition) {
        repo.findById(id)
                .map(c -> {
                    c.setName(competition.getName());
                    c.setCategoryId(competition.getCategoryId());
                    c.setCompetitionType(competition.getCompetitionType());
                    c.setGender(competition.getGender());
                    return repo.save(c);
                })
                .orElseThrow(NotFoundException::new);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
