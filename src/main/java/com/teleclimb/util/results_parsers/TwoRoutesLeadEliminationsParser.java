package com.teleclimb.util.results_parsers;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.dto.model.*;
import com.teleclimb.dto.model.score.ScoreLead;
import com.teleclimb.service.RefereePositionService;
import com.teleclimb.service.start.StartService;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class TwoRoutesLeadEliminationsParser {
    private final Gson gson = GsonConfig.gson();

    private final RefereePositionService positionService;
    private final StartService startService;

    private final Round round;

    private List<ParticipantData> participantsData;


    public TwoRoutesLeadEliminationsParser(List<ParticipantWithMeta> participantResults, Round round, RefereePositionService positionService, StartService startService) {
        this.positionService = positionService;
        this.startService = startService;

        this.round = round;

        this.participantsData = prepareDataObjects(participantResults);
    }

    public List<ParticipantWithMeta> process() {
        calculatePlaceA();
        calculatePlaceB();
        calculateResultAndSort();

        return processToParticipantsResult();
    }


    private List<ParticipantData> prepareDataObjects(List<ParticipantWithMeta> participantsResults) {
        List<ParticipantData> participantsData = new ArrayList<>();

        List<RefereePosition> positions = positionService.getAllByRoundId(round.getId());
        if (positions.size() != 2) throw new RuntimeException("Number of referee positions should be 2");
        RefereePosition positionA = positions.get(0);
        RefereePosition positionB = positions.get(1);

        for (ParticipantWithMeta participant : participantsResults) {
            Start startA = startService.getByRefereePositionIdAndParticipantId(positionA.getId(), participant.getId());
            Start startB = startService.getByRefereePositionIdAndParticipantId(positionB.getId(), participant.getId());

            ScoreLead scoreA = gson.fromJson(startA.getScore(), ScoreLead.class);
            ScoreLead scoreB = gson.fromJson(startB.getScore(), ScoreLead.class);

            ParticipantData participantData = new ParticipantData();
            participantData.setParticipant(participant);
            participantData.setScoreA(scoreA);
            participantData.setScoreB(scoreB);

            participantsData.add(participantData);
        }
        return participantsData;
    }

    private void calculatePlaceA() {
        participantsData.sort((o1, o2) -> {
            if (o1.scoreA == null && o2.scoreA == null) return 0;
            if (o1.scoreA == null) return -1;
            if (o2.scoreA == null) return 1;
            return o1.scoreA.compare(o2.scoreA);
        });

        int lastSetIndex = -1; //this is important!
        for (int i = 0; i < participantsData.size(); i++) {
            if (isNextDifferentOrEndA(i)) {
                for (int j = lastSetIndex + 1; j <= i; j++) {
                    participantsData.get(j).setPlaceA((lastSetIndex + 2.0 + i + 1.0) / 2); // place calculating formula
                }
                lastSetIndex = i;
            }
        }
    }

    private boolean isNextDifferentOrEndA(int i) {
        if (i == participantsData.size() - 1) return true;

        ScoreLead scoreThis = participantsData.get(i).getScoreA();
        ScoreLead scoreNext = participantsData.get(i + 1).getScoreA();
        if (scoreThis == null && scoreNext == null) return true;
        if (scoreNext == null) return false;
        return scoreThis.compare(scoreNext) != 0;
    }

    private void calculatePlaceB() {
        participantsData.sort((o1, o2) -> {
            if (o1.scoreB == null && o2.scoreB == null) return 0;
            if (o1.scoreB == null) return -1;
            if (o2.scoreB == null) return 1;

            return o1.scoreB.compare(o2.scoreB);
        });

        int lastSetIndex = -1; //this is important!
        for (int i = 0; i < participantsData.size(); i++) {
            if (isNextDifferentOrEndB(i)) {
                for (int j = lastSetIndex + 1; j <= i; j++) {
                    participantsData.get(j).setPlaceB((lastSetIndex + 2.0 + i + 1.0) / 2); // place calculating formula
                }
                lastSetIndex = i;
            }
        }
    }

    private boolean isNextDifferentOrEndB(int i) {
        if (i == participantsData.size() - 1) return true;

        ScoreLead scoreThis = participantsData.get(i).getScoreB();
        ScoreLead scoreNext = participantsData.get(i + 1).getScoreB();
        if (scoreThis == null && scoreNext == null) return true;
        if (scoreNext == null) return false;
        return scoreThis.compare(scoreNext) != 0;
    }

    private void calculateResultAndSort() {
        for (ParticipantData results : participantsData) {
            results.setResult(Math.sqrt(results.placeA * results.placeB));
        }
        participantsData.sort(Comparator.comparing(ParticipantData::getResult));
    }


    private List<ParticipantWithMeta> processToParticipantsResult() {
        List<ParticipantWithMeta> participantsWithMeta = new ArrayList<>();

        int place = 1;
        for (ParticipantData data : participantsData) {
            ParticipantWithMeta participant = data.getParticipant();
            participant.setTopRoundNumber(round.getSequenceNumber());
            participant.setPreviousRoundPlace(place);

            List<Meta> results = new ArrayList<>();
            results.add(new Meta("1_eliminations_1_score_A", data.scoreA == null ? "" : data.scoreA.toString()));
            results.add(new Meta("1_eliminations_2_place_A", data.placeA == null ? "" : data.placeA.toString()));
            results.add(new Meta("1_eliminations_3_score_B", data.scoreB == null ? "" : data.scoreB.toString()));
            results.add(new Meta("1_eliminations_4_place_B", data.placeB == null ? "" : data.placeB.toString()));
            results.add(new Meta("1_eliminations_5_result", data.result == null ? "" : data.result.toString()));
            participant.setMeta(results);

            participantsWithMeta.add(participant);

            place++;
        }
        return participantsWithMeta;
    }

    @Data
    private class ParticipantData {
        private ParticipantWithMeta participant;
        private ScoreLead scoreA, scoreB;
        private Double placeA, placeB;
        private Double result;
    }
}
