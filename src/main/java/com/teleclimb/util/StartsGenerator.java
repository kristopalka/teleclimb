package com.teleclimb.util;

import com.teleclimb.enums.StartsGenerationMethod;
import com.teleclimb.rest.dto.ParticipantDto;
import com.teleclimb.rest.dto.RoundDto;
import com.teleclimb.rest.dto.RouteDto;
import com.teleclimb.rest.dto.StartDto;

import java.util.*;

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

    public static <T> TreeSet<T> randomizeSet(Collection<T> collection) {
        List<T> shuffleMe = new ArrayList<T>(collection);
        Collections.shuffle(shuffleMe);
        return new TreeSet<>(shuffleMe);
    }

    private void generateLeadClassicFinal() {
        //todo generowanko :)
    }

    private void generateLeadClassicEliminations() {
        if (routes.size() != 2) throw new RuntimeException("should be 2 rotes linked to round id: " + round.getId());
        RouteDto routeA = routes.get(0);
        RouteDto routeB = routes.get(1);

        TreeSet<ParticipantDto> randomizedParticipants = randomizeSet(participants);

        int numberOfParticipants = participants.size();
        int routeACounter = 0;
        int routeBCounter = (int) Math.ceil(((double) numberOfParticipants) / 2);
        for (ParticipantDto participant : randomizedParticipants) {
            StartDto startOnA = StartDto.builder()
                    .round(round)
                    .route(routeA)
                    .participant(participant)
                    .routeSequenceNumber(routeACounter)
                    .build();

            StartDto startOnB = StartDto.builder()
                    .round(round)
                    .route(routeB)
                    .participant(participant)
                    .routeSequenceNumber(routeBCounter)
                    .build();

            starts.add(startOnA);
            starts.add(startOnB);

            routeACounter++;
            routeBCounter++;
        }
    }
}
