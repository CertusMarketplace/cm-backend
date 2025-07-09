package pe.edu.certus.worksmodule.configuration.mappers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;
import pe.edu.certus.worksmodule.logic.adapters.mapper.CommentMapper;

@Configuration
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // Para que el IDE no se queje de la inyección
public class CommentMapperConfig {

    @Bean
    public CommentMapper commentMapper(ForPeople<PeopleModel, Long> forPeople) {
        // Pasamos la dependencia ForPeople al constructor
        return new CommentMapper(forPeople);
    }
}