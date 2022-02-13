package com.teleclimb.rest.start;

import com.teleclimb.responses.error.exception.NotFoundException;
import com.teleclimb.rest.start.Start;
import com.teleclimb.rest.start.StartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StartService {
    private final StartRepository startRepo;



    public List<StartDto> getAll() {
        return startRepo.findAll().stream().map(StartService::entityToDto).collect(Collectors.toList());
    }

    public StartDto get(Long id) {
        return entityToDto(startRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found start with id: " + id)));
    }



    public static StartDto entityToDto(Start start) {
        StartDto dto = new StartDto();

        dto.setId(start.getId());
        dto.setRound(start.getRound());
        dto.setRoute(start.getRoute());
        dto.setContestant(start.getContestant());
        dto.setResult(start.getResult());

        return dto;
    }

    public static Start dtoToEntity(StartDto dto) {
        Start start = new Start();

        start.setId(dto.getId());
        start.setRound(dto.getRound());
        start.setRoute(dto.getRoute());
        start.setContestant(dto.getContestant());
        start.setResult(dto.getResult());

        return start;
    }
}
