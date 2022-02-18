package com.teleclimb.config;

import com.teleclimb.rest.services.TestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner loadData(TestService testService) {
        return args -> {
            // this code will be done right after app initialization
            testService.testWeird();
        };
    }
}
