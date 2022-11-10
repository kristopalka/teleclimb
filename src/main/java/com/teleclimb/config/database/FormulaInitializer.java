package com.teleclimb.config.database;

import com.google.gson.Gson;
import com.teleclimb.config.GsonConfig;
import com.teleclimb.dto.enums.Discipline;
import com.teleclimb.dto.enums.ResultCalculatingFunction;
import com.teleclimb.dto.enums.StartsGenerationMethod;
import com.teleclimb.dto.model.Formula;
import com.teleclimb.dto.model.Round;
import com.teleclimb.service.FormulaService;

import java.util.ArrayList;
import java.util.List;

public class FormulaInitializer {
    private static final Gson gson = GsonConfig.get();
    private static FormulaService formulaService;

    public static void initFormulas(FormulaService service) {
        formulaService = service;

        standardLead();
        standardLeadWithSemifinal();
    }

    private static void standardLead() {
        List<Round> rounds = new ArrayList<>();
        rounds.add(Round.builder()
                .name("Eliminacje")
                .sequenceNumber(0)
                .resultCalculatingFunction(ResultCalculatingFunction.TWO_ROUTES_LEAD_ELIMINATIONS)
                .numberOfRoutes(2)
                .maxParticipants(Integer.MAX_VALUE)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_ELIMINATIONS)
                .build());

        rounds.add(Round.builder()
                .name("Finał")
                .sequenceNumber(1)
                .resultCalculatingFunction(ResultCalculatingFunction.ONE_ROUTE_LEAD_FINAL)
                .numberOfRoutes(1)
                .maxParticipants(8)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_FINAL)
                .build());

        formulaService.add(Formula.builder()
                .name("Standardowe prowadzenie")
                .description("Dwie rundy: eliminacyjna i finałowa. Dwie drogi eliminacyjne i jedna finałowa - każdy zawodnik wspina się po obydwu. 8 zawodników w finale")
                .discipline(Discipline.LEAD)
                .numberOfRounds(2)
                .jsonConfiguration(gson.toJson(rounds))
                .build());

    }

    private static void standardLeadWithSemifinal() {
        List<Round> rounds = new ArrayList<>();
        rounds.add(Round.builder()
                .name("Eliminacje")
                .sequenceNumber(0)
                .resultCalculatingFunction(ResultCalculatingFunction.TWO_ROUTES_LEAD_ELIMINATIONS)
                .numberOfRoutes(2)
                .maxParticipants(Integer.MAX_VALUE)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_ELIMINATIONS)
                .build());

        rounds.add(Round.builder()
                .name("Półfinał")
                .sequenceNumber(1)
                .resultCalculatingFunction(ResultCalculatingFunction.ONE_ROUTE_LEAD_FINAL)
                .numberOfRoutes(1)
                .maxParticipants(26)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_FINAL)
                .build());

        rounds.add(Round.builder()
                .name("Finał")
                .sequenceNumber(2)
                .resultCalculatingFunction(ResultCalculatingFunction.ONE_ROUTE_LEAD_FINAL)
                .numberOfRoutes(1)
                .maxParticipants(8)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_FINAL)
                .build());

        formulaService.add(Formula.builder()
                .name("Standardowe prowadzenie z półfinałem")
                .description("Trzy rundy: eliminacyjna, półfinałowa i finałowa. Dwie drogi eliminacyjne, jedna półfinałowa i jedna finałowa - każdy zawodnik wspina się po obydwu. 26 zawodników w półfinale i 8 w finale")
                .discipline(Discipline.LEAD)
                .numberOfRounds(2)
                .jsonConfiguration(gson.toJson(rounds))
                .build());

    }


}
