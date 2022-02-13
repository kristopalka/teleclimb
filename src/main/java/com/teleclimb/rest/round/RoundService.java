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
        return roundRepo.findAll().stream().map(RoundService::entityToDto).collect(Collectors.toList());
    }

    public RoundDto get(Long id) {
        return entityToDto(roundRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found round with id: " + id)));
    }



    public static RoundDto entityToDto(Round round) {
        RoundDto dto = new RoundDto();

        dto.setId(round.getId());
        dto.setCompetition(round.getCompetition());
        dto.setName(round.getName());
        dto.setMaxContestants(round.getMaxContestants());

        return dto;
    }

    public static Round dtoToEntity(RoundDto dto) {
        Round round = new Round();

        round.setId(dto.getId());
        round.setCompetition(dto.getCompetition());
        round.setName(dto.getName());
        round.setMaxContestants(dto.getMaxContestants());

        return round;
    }
}
