package com.teleclimb.util;

import com.teleclimb.rest.entities.Competition;
import com.teleclimb.rest.entities.Round;

import java.util.ArrayList;
import java.util.List;

public class RoundsGenerator {
    private final Competition competition;
    private final List<Round> rounds;

    public RoundsGenerator(Competition competition) {
        this.competition = competition;
        rounds = new ArrayList<>();
    }

    public List<Round> generate() {
        String identifier = competition.getFormula().getIdentifier();

        switch (identifier) {
            case "LEAD_DEFAULT":
                generateLeadDefault();
                break;
            default:
                throw new RuntimeException("wrong formula identifier");
        }

        return rounds;
    }

    private void generateLeadDefault() {
        //TODO można to zapisać jako obiekty w formie json, to może być w bazie w nowym polu w FORMULA
        Round eliminationRound = Round.builder()
                .competition(competition)
                .name("Eliminacyje")
                .sequenceNumber(1)
                .resultCalculatingFunction("?") //todo napisać funkcję
                .numberOfRoutes(2)
                .maxParticipants(Integer.MAX_VALUE)
                .build();

        Round finalRound = Round.builder()
                .competition(competition)
                .name("Finał")
                .sequenceNumber(2)
                .resultCalculatingFunction("?") //todo napisać funkcję
                .numberOfRoutes(1)
                .maxParticipants(8)
                .build();

        rounds.add(eliminationRound);
        rounds.add(finalRound);
    }
}
