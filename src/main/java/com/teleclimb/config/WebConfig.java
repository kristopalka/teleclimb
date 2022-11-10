package com.teleclimb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedOriginPatterns("http://localhost:3000");

        registry.addMapping("/mobile/*")
                .allowedMethods("GET", "PUT")
                .allowedOriginPatterns("");

        registry.addMapping("/test")
                .allowedMethods("GET")
                .allowedOriginPatterns("");
    }
}