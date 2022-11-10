package com.teleclimb.config.database;

import com.teleclimb.dto.model.Category;
import com.teleclimb.service.CategoryService;
import com.teleclimb.service.FormulaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Initializer {
    @Bean
    public CommandLineRunner loadData(CategoryService categoryService, FormulaService formulaService) {
        return args -> {
            // this code will be done right after app initialization
            initCategories(categoryService);
            FormulaInitializer.initFormulas(formulaService);
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

}
