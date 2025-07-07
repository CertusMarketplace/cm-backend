package pe.edu.certus.worksmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.worksmodule.logic.adapters.mapper.CommentMapper;

@Configuration
public class CommentMapperConfig {

    @Bean
    public CommentMapper commentMapper() {
        return new CommentMapper();
    }
}