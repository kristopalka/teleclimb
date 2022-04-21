package com.teleclimb.util;

import com.teleclimb.enums.StartsGenerationMethod;
import com.teleclimb.rest.dto.Participant;
import com.teleclimb.rest.dto.RefereePosition;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.dto.Start;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StartsGenerator {

    private final Round round;
    private final List<Participant> participants;
    private final List<RefereePosition> positions;
    private final List<Start> starts;

    public StartsGenerator(Round round, List<Participant> participants, List<RefereePosition> positions) {
        this.round = round;
        this.participants = participants;
        this.positions = positions;
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

        RefereePosition positionA = positions.get(0);
        RefereePosition positionB = positions.get(1);

        ArrayList<Participant> randomizedParticipants = randomizeSet(participants);
        int numberOfParticipants = randomizedParticipants.size();

        int iteratorA = 0;
        int iteratorB = (int) Math.floor(((double) numberOfParticipants) / 2);
        for (int i = 0; i < numberOfParticipants; i++) {
            Start startOnA = Start.builder()
                    .refereePositionId(positionA.getId())
                    .participantId(randomizedParticipants.get(iteratorA).getId())
                    .positionSequenceNumber(i)
                    .build();

            starts.add(startOnA);
            iteratorA++;

            Start startOnB = Start.builder()
                    .refereePositionId(positionB.getId())
                    .participantId(randomizedParticipants.get(iteratorB).getId())
                    .positionSequenceNumber(i)
                    .build();

            starts.add(startOnB);
            iteratorB++;
            iteratorB = iteratorB % numberOfParticipants;
        }
    }

    private void generateLeadClassicFinal() {
        //todo generowanko :)
    }
}
