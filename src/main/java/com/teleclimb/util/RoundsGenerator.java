package com.teleclimb.util;

import com.teleclimb.enums.StartsGenerationMethod;
import com.teleclimb.rest.entities.CompetitionEntity;
import com.teleclimb.rest.entities.RoundEntity;

import java.util.ArrayList;
import java.util.List;

public class RoundsGenerator {
    private final CompetitionEntity competition;
    private final List<RoundEntity> roundEntities;

    public RoundsGenerator(CompetitionEntity competitionEntity) {
        this.competition = competitionEntity;
        roundEntities = new ArrayList<>();
    }

    public List<RoundEntity> generate() {
        String identifier = competition.getFormula().getIdentifier();

        switch (identifier) {
            case "LEAD_DEFAULT":
                generateLeadDefault();
                break;
            default:
                throw new RuntimeException("wrong formula identifier");
        }

        return roundEntities;
    }

    private void generateLeadDefault() {
        //TODO można to zapisać jako obiekty w formie json, to może być w bazie w nowym polu w FORMULA
        RoundEntity eliminationRoundEntity = RoundEntity.builder()
                .competition(competition)
                .name("Eliminacyje")
                .sequenceNumber(1)
                .resultCalculatingFunction("?") //todo napisać funkcję
                .numberOfRoutes(2)
                .maxParticipants(Integer.MAX_VALUE)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_ELIMINATIONS)
                .build();

        RoundEntity finalRoundEntity = RoundEntity.builder()
                .competition(competition)
                .name("Finał")
                .sequenceNumber(2)
                .resultCalculatingFunction("?") //todo napisać funkcję
                .numberOfRoutes(1)
                .maxParticipants(8)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_FINAL)
                .build();

        roundEntities.add(eliminationRoundEntity);
        roundEntities.add(finalRoundEntity);
    }
}
