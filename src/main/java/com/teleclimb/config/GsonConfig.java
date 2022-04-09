package com.teleclimb.config;


import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class GsonConfig {
    @Bean
    public static Gson gson() {
        String pattern = "HH:mm:ss.SSS";

        return new GsonBuilder()
                .registerTypeAdapter(LocalTime.class, new JsonDeserializer<LocalTime>() {
                    @Override
                    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return LocalTime.parse(json.getAsString(), DateTimeFormatter.ofPattern(pattern));
                    }
                }).registerTypeAdapter(LocalTime.class, new JsonSerializer<LocalTime>() {
                    @Override
                    public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(DateTimeFormatter.ofPattern(pattern).format(localTime));
                    }
                }).create();
    }
}
