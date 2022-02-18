package com.teleclimb.util;

import com.teleclimb.enums.StartsGenerationMethod;
import com.teleclimb.rest.dto.Competition;
import com.teleclimb.rest.dto.Formula;
import com.teleclimb.rest.dto.Round;

import java.util.ArrayList;
import java.util.List;

public class RoundsGenerator {
    private final Competition competition;
    private final Formula formula;
    private final List<Round> rounds;

    public RoundsGenerator(Competition competition, Formula formula) {
        this.competition = competition;
        this.formula = formula;
        rounds = new ArrayList<>();
    }

    public List<Round> generate() {
        String identifier = formula.getIdentifier();

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
                .competitionId(competition.getId())
                .name("Eliminacyje")
                .sequenceNumber(0)
                .resultCalculatingFunction("?") //todo napisać funkcję
                .numberOfRoutes(2)
                .maxParticipants(Integer.MAX_VALUE)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_ELIMINATIONS)
                .build();

        Round finalRound = Round.builder()
                .competitionId(competition.getId())
                .name("Finał")
                .sequenceNumber(1)
                .resultCalculatingFunction("?") //todo napisać funkcję
                .numberOfRoutes(1)
                .maxParticipants(8)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_FINAL)
                .build();

        rounds.add(eliminationRound);
        rounds.add(finalRound);
    }
}
