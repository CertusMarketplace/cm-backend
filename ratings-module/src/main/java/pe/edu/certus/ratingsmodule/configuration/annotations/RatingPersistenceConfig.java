package pe.edu.certus.ratingsmodule.configuration.annotations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pe.edu.certus.ratingsmodule.repository")
public class RatingPersistenceConfig {
}
