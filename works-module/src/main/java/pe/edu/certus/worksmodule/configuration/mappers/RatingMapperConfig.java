package pe.edu.certus.worksmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.worksmodule.logic.adapters.mapper.RatingMapper;

@Configuration
public class RatingMapperConfig {

    @Bean
    public RatingMapper ratingMapper() {
        return new RatingMapper();
    }
}
