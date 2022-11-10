package com.teleclimb.util.starts_generators;

import com.teleclimb.dto.model.Participant;
import com.teleclimb.dto.model.RefereePosition;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.Start;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ClassicLeadEliminationsGenerator implements Generator {

    public static <T> ArrayList<T> randomizeSet(Collection<T> collection) {
        List<T> shuffleMe = new ArrayList<T>(collection);
        Collections.shuffle(shuffleMe);
        return new ArrayList<>(shuffleMe);
    }

    // two routes, random list on first route, list moved for a half on the second route
    @Override
    public List<Start> generate(Round round, List<Participant> participants, List<RefereePosition> positions) {
        List<Start> starts = new ArrayList<>();

        RefereePosition positionA = positions.get(0);
        RefereePosition positionB = positions.get(1);

        ArrayList<Participant> randomizedParticipants = randomizeSet(participants);
        int numberOfParticipants = randomizedParticipants.size();

        int iteratorA = 0;
        int iteratorB = (int) Math.floor(((double) numberOfParticipants) / 2);
        for (int i = 0; i < numberOfParticipants; i++) {
            Start startA = Start.builder()
                    .refereePositionId(positionA.getId())
                    .participantId(randomizedParticipants.get(iteratorA).getId())
                    .positionSequenceNumber(i)
                    .build();

            starts.add(startA);
            iteratorA++;

            Start startB = Start.builder()
                    .refereePositionId(positionB.getId())
                    .participantId(randomizedParticipants.get(iteratorB).getId())
                    .positionSequenceNumber(i)
                    .build();

            starts.add(startB);
            iteratorB++;
            iteratorB = iteratorB % numberOfParticipants;
        }
        return starts;
    }

}
