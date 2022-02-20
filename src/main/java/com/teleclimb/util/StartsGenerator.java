package com.teleclimb.util;

import com.teleclimb.enums.StartsGenerationMethod;
import com.teleclimb.rest.dto.Participant;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.dto.Route;
import com.teleclimb.rest.dto.Start;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StartsGenerator {
    private final Round round;
    private final List<Participant> participants;
    private final List<Route> routes;
    private final List<Start> starts;

    public StartsGenerator(Round round, List<Participant> participants, List<Route> routes) {
        this.round = round;
        this.participants = participants;
        this.routes = routes;
        this.starts = new ArrayList<>();
    }

    public List<Start> generate() {
        StartsGenerationMethod method = round.getStartsGenerationMethod();

        switch (method) {
            case LEAD_CLASSIC_ELIMINATIONS -> generateLeadClassicEliminations();
            case LEAD_CLASSIC_FINAL -> generateLeadClassicFinal();
            default -> throw new RuntimeException("wrong formula identifier");
        }

        return starts;
    }

    public static <T> ArrayList<T> randomizeSet(Collection<T> collection) {
        List<T> shuffleMe = new ArrayList<T>(collection);
        Collections.shuffle(shuffleMe);
        return new ArrayList<>(shuffleMe);
    }

    // two routes, random list on first route, list moved for a half on the second route
    private void generateLeadClassicEliminations() {
        if (routes.size() != 2)
            throw new RuntimeException("should be 2 routes added to round with id: " + round.getId());
        Route routeA = routes.get(0);
        Route routeB = routes.get(1);

        ArrayList<Participant> randomizedParticipants = randomizeSet(participants);
        int numberOfParticipants = randomizedParticipants.size();

        int iteratorA = 0;
        int iteratorB = (int) Math.floor(((double) numberOfParticipants) / 2);
        for (int i = 0; i < numberOfParticipants; i++) {
            Start startOnA = Start.builder().roundId(round.getId())
                    .routeId(routeA.getId())
                    .participantId(randomizedParticipants.get(iteratorA).getId())
                    .routeSequenceNumber(i).build();

            starts.add(startOnA);
            iteratorA++;

            Start startOnB = Start.builder().roundId(round.getId())
                    .routeId(routeB.getId())
                    .participantId(randomizedParticipants.get(iteratorB).getId())
                    .routeSequenceNumber(i).build();

            starts.add(startOnB);
            iteratorB++;
            iteratorB = iteratorB % numberOfParticipants;
        }
    }

    private void generateLeadClassicFinal() {
        //todo generowanko :)
    }
}
