package pe.edu.certus.worksmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.worksmodule.repository.adapters.mapper.CommentPersistenceMapper;

@Configuration
public class CommentMapperPersistenceConfig {
    @Bean
    public CommentPersistenceMapper commentPersistenceMapper() {
        return new CommentPersistenceMapper();
    }
}
