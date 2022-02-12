package com.teleclimb.rest.services;

import com.teleclimb.rest.exceptions.NotFoundException;
import com.teleclimb.rest.entity.Contestant;
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
                .orElseThrow(NotFoundException::new);
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
                .orElseThrow(NotFoundException::new);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
