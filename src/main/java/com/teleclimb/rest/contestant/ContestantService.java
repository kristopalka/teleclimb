package com.teleclimb.rest.contestant;

import com.teleclimb.responses.error.exception.NotFoundException;
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
                .orElseThrow(() -> new NotFoundException("Not found contestant with id: " + id));
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
                .orElseThrow(() -> new NotFoundException("Not found contestant with id: " + id));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
