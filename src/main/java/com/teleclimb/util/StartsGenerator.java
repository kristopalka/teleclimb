package com.teleclimb.util;

import com.teleclimb.enums.StartsGenerationMethod;
import com.teleclimb.rest.dto.Participant;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.dto.Route;
import com.teleclimb.rest.dto.Start;

import java.util.*;

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

    public static <T> TreeSet<T> randomizeSet(Collection<T> collection) {
        List<T> shuffleMe = new ArrayList<T>(collection);
        Collections.shuffle(shuffleMe);
        return new TreeSet<>(shuffleMe);
    }


    private void generateLeadClassicEliminations() {
        if (routes.size() != 2) throw new RuntimeException("should be 2 routes linked to round id: " + round.getId());
        Route routeA = routes.get(0);
        Route routeB = routes.get(1);

        TreeSet<Participant> randomizedParticipants = randomizeSet(participants);

        System.out.println("XDDDDDDDDDDDDDD");
        int numberOfParticipants = participants.size();
        int routeACounter = 0;
        int routeBCounter = (int) Math.ceil(((double) numberOfParticipants) / 2);
        for (Participant participant : randomizedParticipants) {
            Start startOnA = Start.builder()
                    .roundId(round.getId())
                    .routeId(routeA.getId())
                    .participantId(participant.getId())
                    .routeSequenceNumber(routeACounter)
                    .build();

            Start startOnB = Start.builder()
                    .roundId(round.getId())
                    .routeId(routeB.getId())
                    .participantId(participant.getId())
                    .routeSequenceNumber(routeBCounter)
                    .build();

            starts.add(startOnA);
            System.out.println(startOnA);
            starts.add(startOnB);
            System.out.println(startOnB);

            routeACounter++;
            routeBCounter++;
        }
    }

    private void generateLeadClassicFinal() {
        //todo generowanko :)
    }
}
