package com.teleclimb.rest.contestant;

import com.teleclimb.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestantService {
    private final ContestantRepository repo;

    public List<ContestantEntity> getAll() {
        return repo.findAll();
    }

    public ContestantEntity get(Long id) {
        return repo.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public void add(ContestantEntity contestant) {
        repo.save(contestant);
    }

    public void update(Long id, ContestantEntity contestant) {
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