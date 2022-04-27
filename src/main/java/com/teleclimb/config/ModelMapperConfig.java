package com.teleclimb.config;

import com.teleclimb.dto.model.Competition;
import com.teleclimb.dto.model.Start;
import com.teleclimb.entitie.CompetitionEntity;
import com.teleclimb.entitie.StartEntity;
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
                map(source.getCategory().getName()).setCategoryName(null);
                map(source.getFormula().getDiscipline()).setDiscipline(null);
                map(source.getFormula().getNumberOfRounds()).setNumberOfRounds(null);
            }
        });

        mapper.addMappings(new PropertyMap<StartEntity, Start>() {
            @Override
            protected void configure() {
                map(source.getRefereePosition().getRound().getCompetition().getFormula().getDiscipline()).setDiscipline(null);
                map(source.getRefereePosition().getRound().getState()).setRoundState(null);
                map(source.getRefereePosition().getRound().getSequenceNumber()).setRoundSequenceNumber(null);
            }
        });

        return mapper;
    }
}
