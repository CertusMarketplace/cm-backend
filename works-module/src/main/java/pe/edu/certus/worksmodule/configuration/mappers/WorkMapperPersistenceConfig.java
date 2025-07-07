package pe.edu.certus.worksmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.worksmodule.repository.adapters.mapper.WorkPersistenceMapper;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingComment;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingRating;

@Configuration
public class WorkMapperPersistenceConfig {
    @Bean
    public WorkPersistenceMapper workPersistenceMapper( ForBridgingRating forBridgingRating, ForBridgingComment forBridgingComment) {
        return new WorkPersistenceMapper(forBridgingRating, forBridgingComment);
    }
}
