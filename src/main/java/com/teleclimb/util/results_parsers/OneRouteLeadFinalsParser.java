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
            ScoreLead score;
            boolean startsThisRound;
            try {
                Start start = startService.getByRefereePositionIdAndParticipantId(position.getId(), participant.getId());
                score = gson.fromJson(start.getScore(), ScoreLead.class);
                startsThisRound = true;
            } catch (RuntimeException e) {
                score = null;
                startsThisRound = false;
            }

            ParticipantData participantData = new ParticipantData();
            participantData.setParticipant(participant);
            participantData.setScore(score);
            participantData.setStartsThisRound(startsThisRound);

            participantsData.add(participantData);
        }
        return participantsData;
    }

    public List<ParticipantWithMeta> process() {
        participantsData.sort((o1, o2) -> {
            if (ScoreLead.compareNullSafe(o1.score, o2.score) == 0)
                return o1.participant.getPlace() - o2.participant.getPlace();
            return ScoreLead.compareNullSafe(o1.score, o2.score);
        });

        return processDataToMeta();
    }

    private List<ParticipantWithMeta> processDataToMeta() {
        List<ParticipantWithMeta> participantsWithMeta = new ArrayList<>();

        int place = 1;
        for (int i = 0; i < participantsData.size(); i++) {
            ParticipantWithMeta participant = participantsData.get(i).getParticipant();
            if (participantsData.get(i).startsThisRound)
                participant.getMeta().addAll(getMetas(participantsData.get(i)));

            participant.setPlace(place);
            if (isNextDifferentOrEndScore(i)) place++;

            participantsWithMeta.add(participant);
        }
        return participantsWithMeta;
    }

    private boolean isNextDifferentOrEndScore(int i) {
        if (i == participantsData.size() - 1) return true;

        ScoreLead resultThis = participantsData.get(i).getScore();
        ScoreLead resultNext = participantsData.get(i + 1).getScore();

        return ScoreLead.compareNullSafe(resultThis, resultNext) != 0;
    }

    private List<Meta> getMetas(ParticipantData data) {
        List<Meta> results = new ArrayList<>();
        results.add(new Meta("3_final_score", data.score == null ? "" : data.score.toString()));
        return results;
    }

    @Data
    private class ParticipantData {
        private ParticipantWithMeta participant;
        private Boolean startsThisRound;
        private ScoreLead score;
        private Double place;
    }
}

