package pe.edu.certus.worksmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.worksmodule.repository.adapters.mapper.WorkPersistenceMapper;

@Configuration
public class WorkMapperPersistenceConfig {
    @Bean
    public WorkPersistenceMapper workPersistenceMapper() {
        return new WorkPersistenceMapper();
    }
}
