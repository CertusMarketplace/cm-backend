package pe.edu.certus.worksmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.worksmodule.logic.adapters.mapper.WorkMapper;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingComment;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingRating;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingWork;

@Configuration
public class WorkMapperConfig {

    @Bean
    public ForMappingWork workMapper(ForMappingRating forMappingRating, ForMappingComment forMappingComment) {
        return new WorkMapper(forMappingRating, forMappingComment);
    }
}