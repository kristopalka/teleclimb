package com.teleclimb.util;

import com.teleclimb.enums.StartsGenerationMethod;
import com.teleclimb.rest.dto.ParticipantDto;
import com.teleclimb.rest.dto.RoundDto;
import com.teleclimb.rest.dto.RouteDto;
import com.teleclimb.rest.dto.StartDto;

import java.util.ArrayList;
import java.util.List;

public class StartsGenerator {
    private final RoundDto round;
    private final List<ParticipantDto> participants;
    private final List<RouteDto> routes;
    private final List<StartDto> starts;

    public StartsGenerator(RoundDto round, List<ParticipantDto> participants, List<RouteDto> routes) {
        this.round = round;
        this.participants = participants;
        this.routes = routes;
        this.starts = new ArrayList<>();
    }

    public List<StartDto> generate() {
        StartsGenerationMethod method = round.getStartsGenerationMethod();

        switch (method) {
            case LEAD_CLASSIC_ELIMINATIONS -> generateLeadClassicEliminations();
            case LEAD_CLASSIC_FINAL -> generateLeadClassicFinal();
            default -> throw new RuntimeException("wrong formula identifier");
        }

        return starts;
    }

    private void generateLeadClassicEliminations() {
        //todo generowanko :)
    }

    private void generateLeadClassicFinal() {
        //todo generowanko :)
    }
}
