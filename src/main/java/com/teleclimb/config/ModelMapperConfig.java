package com.teleclimb.config;

import com.teleclimb.rest.dto.Competition;
import com.teleclimb.rest.dto.Start;
import com.teleclimb.rest.entities.CompetitionEntity;
import com.teleclimb.rest.entities.StartEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();


        mapper.addMappings(new PropertyMap<CompetitionEntity, Competition>() {
            @Override
            protected void configure() {
                map(source.getFormula().getDiscipline()).setDiscipline(null);
            }
        });

        mapper.addMappings(new PropertyMap<StartEntity, Start>() {
            @Override
            protected void configure() {
                map(source.getRefereePosition().getRound().getCompetition().getFormula().getDiscipline()).setDiscipline(null);
            }
        });


        return mapper;
    }
}
