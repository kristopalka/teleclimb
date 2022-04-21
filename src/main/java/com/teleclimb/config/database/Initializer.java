package com.teleclimb.config.database;

import com.google.gson.Gson;
import com.teleclimb.enums.Discipline;
import com.teleclimb.enums.StartsGenerationMethod;
import com.teleclimb.rest.dto.Category;
import com.teleclimb.rest.dto.Formula;
import com.teleclimb.rest.dto.Round;
import com.teleclimb.rest.services.CategoryService;
import com.teleclimb.rest.services.FormulaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Initializer {

    @Bean
    public CommandLineRunner loadData(CategoryService categoryService, FormulaService formulaService) {
        return args -> {
            initCategories(categoryService);
            initFormula(formulaService);
        };
    }

    private void initCategories(CategoryService categoryService) {
        categoryService.add(new Category(null, "Dziecko młodsze", "DM", 0, 9));
        categoryService.add(new Category(null, "Dziecko", "D", 10, 11));
        categoryService.add(new Category(null, "Młodzik", "Mł", 12, 13));
        categoryService.add(new Category(null, "Junior młodszy", "JM", 14, 15));
        categoryService.add(new Category(null, "Junior", "J", 16, 17));
        categoryService.add(new Category(null, "Młodzieżowiec", "M", 18, 19));
        categoryService.add(new Category(null, "Senior", "S", 20, Integer.MAX_VALUE));
    }

    private void initFormula(FormulaService formulaService) {
        Gson gson = new Gson();
        List<Round> rounds = new ArrayList<>();
        rounds.add(Round.builder()
                .name("Eliminacyje")
                .sequenceNumber(0)
                .resultCalculatingFunction("?")
                .numberOfRoutes(2)
                .maxParticipants(Integer.MAX_VALUE)
                .startsGenerationMethod(StartsGenerationMethod.LEAD_CLASSIC_ELIMINATIONS)
                .build());

        rounds.add(Round.builder()
                .name("Finał")
                .sequenceNumber(1)
                .resultCalculatingFunction("?")
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
}
