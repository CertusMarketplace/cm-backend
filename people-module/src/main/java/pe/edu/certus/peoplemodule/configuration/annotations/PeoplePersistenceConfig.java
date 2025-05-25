package pe.edu.certus.peoplemodule.configuration.annotations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pe.edu.certus.peoplemodule.repository")
public class PeoplePersistenceConfig {
}
