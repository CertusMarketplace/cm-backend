package pe.edu.certus.worksmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.worksmodule.logic.adapters.mapper.WorkCategoryMapper;

@Configuration
public class WorkCategoryMapperConfig {

    @Bean
    WorkCategoryMapper workCategoryMapper() {
        return new WorkCategoryMapper();
    }
}