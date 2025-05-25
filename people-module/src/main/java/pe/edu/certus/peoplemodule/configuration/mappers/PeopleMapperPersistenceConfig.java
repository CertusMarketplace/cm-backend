package pe.edu.certus.peoplemodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.peoplemodule.repository.adapters.mapper.PeoplePersistenceMapper;

@Configuration
public class PeopleMapperPersistenceConfig {
    @Bean
    public PeoplePersistenceMapper peoplePersistenceMapper() {
        return new PeoplePersistenceMapper();
    }

}
