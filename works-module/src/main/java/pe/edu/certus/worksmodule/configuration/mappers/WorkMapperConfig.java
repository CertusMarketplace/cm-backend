package pe.edu.certus.worksmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.worksmodule.logic.adapters.mapper.WorkMapper;

@Configuration
public class WorkMapperConfig {
    @Bean
    public WorkMapper workMapper() {
        return new WorkMapper();
    }
}
