package com.teleclimb.rest.round;

import com.teleclimb.responses.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepo;


    public List<RoundDto> getAll() {
        return roundRepo.findAll().stream().map(Round::toDto).collect(Collectors.toList());
    }

    public RoundDto get(Long id) {
        return roundRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found round with id: " + id)).toDto();
    }
}
