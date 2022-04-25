package com.teleclimb.util;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.controller.responses.error.exception.NotImplementedException;
import com.teleclimb.dto.model.RefereePosition;
import com.teleclimb.dto.model.Round;
import com.teleclimb.dto.model.Start;
import com.teleclimb.dto.model.results.ParticipantData;
import com.teleclimb.dto.model.score.ScoreLead;
import com.teleclimb.service.RefereePositionService;
import com.teleclimb.service.start.StartService;

import java.util.List;

public class TwoRoutesLeadEliminations {
    private final static Gson gson = GsonConfig.gson();

    public static List<ParticipantData> process(List<ParticipantData> participantsData, Round round, RefereePositionService positionService, StartService startService) {
        List<RefereePosition> positions = positionService.getAllByRoundId(round.getId());
        if (positions.size() != 2) throw new RuntimeException("Number of referee positions should be 2");
        RefereePosition positionA = positions.get(0);
        RefereePosition positionB = positions.get(1);

        for (ParticipantData participantData : participantsData) {
            Start startA = startService.getByRefereePositionIdAndParticipantId(positionA.getId(), participantData.getId());
            Start startB = startService.getByRefereePositionIdAndParticipantId(positionB.getId(), participantData.getId());

            ScoreLead scoreA = gson.fromJson(startA.getScore(), ScoreLead.class);
            ScoreLead scoreB = gson.fromJson(startB.getScore(), ScoreLead.class);

        }
        throw new NotImplementedException("");
    }

    private static class ParticipantDataForResultPrecessing {
        ParticipantData participantData;
        ScoreLead scoreA, scoreB;
        Double placeA, placeB;
        Double result;
    }
}
