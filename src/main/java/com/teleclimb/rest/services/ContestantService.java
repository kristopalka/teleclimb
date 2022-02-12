package com.teleclimb.rest.services;

import com.teleclimb.responses.error.exceptions.NotFoundException;
import com.teleclimb.rest.entity.Competition;
import com.teleclimb.rest.entity.Contestant;
import com.teleclimb.rest.repository.CompetitionRepository;
import com.teleclimb.rest.repository.ContestantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestantService {
    private final ContestantRepository repo;

    public List<Contestant> getAll() {
        return repo.findAll();
    }

    public Contestant get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found contestant with id: " + id));
    }

    public void add(Contestant contestant) {
        repo.save(contestant);
    }

    public void update(Long id, Contestant contestant) {
        repo.findById(id)
                .map(c -> {
                    c.setName(contestant.getName());
                    c.setLastName(contestant.getLastName());
                    c.setBirthDate(contestant.getBirthDate());
                    c.setClubName(contestant.getClubName());
                    c.setStartNumber(contestant.getStartNumber());
                    return repo.save(c);
                })
                .orElseThrow(() -> new NotFoundException("Not found competition with id: " + id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
