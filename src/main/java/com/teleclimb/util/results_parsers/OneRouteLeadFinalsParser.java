package com.teleclimb.util.results_parsers;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.dto.model.*;
import com.teleclimb.dto.model.score.ScoreLead;
import com.teleclimb.service.RefereePositionService;
import com.teleclimb.service.start.StartService;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class OneRouteLeadFinalsParser {
    private final Gson gson = GsonConfig.gson();

    private final RefereePositionService positionService;
    private final StartService startService;

    private final Round round;

    private List<ParticipantData> participantsData;


    public OneRouteLeadFinalsParser(List<ParticipantWithMeta> participantResults, Round round, RefereePositionService positionService, StartService startService) {
        this.positionService = positionService;
        this.startService = startService;

        this.round = round;

        this.participantsData = prepareParticipantsDataObjects(participantResults);
    }

    private List<ParticipantData> prepareParticipantsDataObjects(List<ParticipantWithMeta> participantsResults) {
        List<ParticipantData> participantsData = new ArrayList<>();

        List<RefereePosition> positions = positionService.getAllByRoundId(round.getId());
        if (positions.size() != 1) throw new RuntimeException("Number of referee positions should be 1");
        RefereePosition position = positions.get(0);


        for (ParticipantWithMeta participant : participantsResults) {
            Start start = startService.getByRefereePositionIdAndParticipantId(position.getId(), participant.getId());
            ScoreLead score = gson.fromJson(start.getScore(), ScoreLead.class);

            ParticipantData participantData = new ParticipantData();
            participantData.setParticipant(participant);
            participantData.setScore(score);

            participantsData.add(participantData);
        }
        return participantsData;
    }

    public List<ParticipantWithMeta> process() {
        participantsData.sort((o1, o2) -> ScoreLead.compareNullSafe(o1.score, o2.score));

        //todo sortuj po poprzednij rundzie jeżeli takie samo

        return processDataToMeta();
    }

    private List<ParticipantWithMeta> processDataToMeta() {
        List<ParticipantWithMeta> participantsWithMeta = new ArrayList<>();

        int place = 1;
        for (ParticipantData data : participantsData) {
            ParticipantWithMeta participant = data.getParticipant();
            participant.setPlace(place);

            List<Meta> results = new ArrayList<>();
            results.add(new Meta("3_final_score", data.score == null ? "" : data.score.toString()));
            participant.setMeta(results);

            participantsWithMeta.add(participant);

            place++;
        }
        return participantsWithMeta;
    }

    @Data
    private class ParticipantData {
        private ParticipantWithMeta participant;
        private ScoreLead score;
        private Double place;
    }
}

