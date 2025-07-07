package pe.edu.certus.worksmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkCategoryPersistenceConfig {

    @Bean
    public CommentMapperPersistenceConfig commentMapperPersistenceConfig() {
        return new CommentMapperPersistenceConfig();
    }
}
