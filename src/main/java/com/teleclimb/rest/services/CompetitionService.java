package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exceptions.NotFoundException;
import com.teleclimb.rest.entity.Competition;
import com.teleclimb.rest.repository.CompetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository repo;

    public List<Competition> getAll() {
        return repo.findAll();
    }

    public Competition get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found competition with id: " + id));
    }

    public void add(Competition competition) {
        repo.save(competition);
    }

    public void update(Long id, Competition competition) {
        repo.findById(id)
                .map(c -> {
                    c.setName(competition.getName());
                    c.setCategoryId(competition.getCategoryId());
                    c.setCompetitionType(competition.getCompetitionType());
                    c.setGender(competition.getGender());
                    return repo.save(c);
                })
                .orElseThrow(() -> new NotFoundException("Not found competition with id: " + id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
