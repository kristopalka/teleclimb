package com.teleclimb.util.starts_generators;

import com.teleclimb.dto.model.Participant;
import com.teleclimb.dto.model.RefereePosition;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.Start;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClassicLeadFinalsGenerator implements Generator {


    @Override
    public List<Start> generate(Round round, List<Participant> participants, List<RefereePosition> positions) {
        List<Start> starts = new ArrayList<>();

        RefereePosition position = positions.get(0);

        participants.sort(Comparator.comparing(Participant::getPlace).reversed());


        int iterator = 0;
        for (Participant participant : participants) {
            Start start = Start.builder()
                    .refereePositionId(position.getId())
                    .participantId(participant.getId())
                    .positionSequenceNumber(iterator)
                    .build();

            starts.add(start);
        }

        return starts;
    }
}
