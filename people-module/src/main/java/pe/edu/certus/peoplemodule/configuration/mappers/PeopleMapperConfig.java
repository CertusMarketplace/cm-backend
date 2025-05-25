package pe.edu.certus.peoplemodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.peoplemodule.logic.adapters.mapper.PeopleMapper;

@Configuration
public class PeopleMapperConfig {
    @Bean
    public PeopleMapper peopleMapper() {
        return new PeopleMapper();
    }
}