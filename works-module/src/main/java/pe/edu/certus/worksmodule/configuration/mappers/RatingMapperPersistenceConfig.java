package pe.edu.certus.worksmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.worksmodule.repository.adapters.mapper.RatingPersistenceMapper;

@Configuration
public class RatingMapperPersistenceConfig {
    @Bean
    public RatingPersistenceMapper ratingPersistenceMapper() {
        return new RatingPersistenceMapper();
    }
}

