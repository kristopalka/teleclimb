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
            // write test endpoint that will be started after run of app
            testService.testWeird();
        };
    }
}
