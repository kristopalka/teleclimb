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

import static com.teleclimb.util.results_parsers.MetaBuilder.*;
import static java.lang.Double.compare;


public class TwoRoutesLeadEliminationsParser {
    private final Gson gson = GsonConfig.get();

    private final StartService startService;

    private final Round round;
    private final List<ParticipantData> participantsData;
    RefereePositionWithRoute positionA;
    RefereePositionWithRoute positionB;


    public TwoRoutesLeadEliminationsParser(List<ParticipantWithMeta> participantsResults, Round round, RefereePositionService positionService, StartService startService) {
        this.startService = startService;

        this.round = round;

        List<RefereePositionWithRoute> positions = positionService.getAllWithRouteByRoundId(round.getId());
        if (positions.size() != 2) throw new RuntimeException("Number of referee positions should be 2");
        positionA = positions.get(0);
        positionB = positions.get(1);

        this.participantsData = prepareParticipantsDataObjects(participantsResults);
    }

    public List<ParticipantWithMeta> process() {
        calculatePlaceA();
        calculatePlaceB();
        calculateResultAndSort();

        return processDataToMeta();
    }


    private List<ParticipantData> prepareParticipantsDataObjects(List<ParticipantWithMeta> participantsResults) {
        List<ParticipantData> participantsData = new ArrayList<>();

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
        participantsData.sort((o1, o2) -> ScoreLead.compareNullSafe(o1.scoreA, o2.scoreA));

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

        return ScoreLead.compareNullSafe(scoreThis, scoreNext) != 0;
    }

    private void calculatePlaceB() {
        participantsData.sort((o1, o2) -> ScoreLead.compareNullSafe(o1.scoreB, o2.scoreB));

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

        return ScoreLead.compareNullSafe(scoreThis, scoreNext) != 0;
    }

    private void calculateResultAndSort() {
        for (ParticipantData results : participantsData) {
            results.setResult(Math.sqrt(results.placeA * results.placeB));
        }
        participantsData.sort(Comparator.comparing(ParticipantData::getResult));
    }


    private List<ParticipantWithMeta> processDataToMeta() {
        List<ParticipantWithMeta> participantsWithMeta = new ArrayList<>();

        int place = 1;
        for (int i = 0; i < participantsData.size(); i++) {
            ParticipantWithMeta participant = participantsData.get(i).getParticipant();
            participant.getMeta().addAll(getMetas(participantsData.get(i)));

            participant.setPlace(place);
            if (isNextDifferentOrEndResult(i)) place++;

            participantsWithMeta.add(participant);
        }
        return participantsWithMeta;
    }

    private boolean isNextDifferentOrEndResult(int i) {
        if (i == participantsData.size() - 1) return true;

        Double resultThis = participantsData.get(i).getResult();
        Double resultNext = participantsData.get(i + 1).getResult();

        if (resultThis == null) return false;
        else if (resultNext == null) return true;
        return compare(resultThis, resultNext) != 0;
    }


    private List<Meta> getMetas(ParticipantData data) {
        List<Meta> results = new ArrayList<>();

        results.add(MetaBuilder.build(round.getName(), positionA.getRoute().getName(), resultStr, data.scoreA));
        results.add(MetaBuilder.build(round.getName(), positionA.getRoute().getName(), placeStr, data.placeA));
        results.add(MetaBuilder.build(round.getName(), positionB.getRoute().getName(), resultStr, data.scoreB));
        results.add(MetaBuilder.build(round.getName(), positionB.getRoute().getName(), placeStr, data.placeB));
        results.add(MetaBuilder.build(round.getName(), empty, resultStr, data.result));

        return results;
    }

    @Data
    private class ParticipantData {
        private ParticipantWithMeta participant;
        private ScoreLead scoreA, scoreB;
        private Double placeA, placeB;
        private Double result;
    }
}
