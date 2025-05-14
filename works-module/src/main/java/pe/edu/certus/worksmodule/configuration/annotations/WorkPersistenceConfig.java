package pe.edu.certus.worksmodule.configuration.annotations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pe.edu.certus.worksmodule.repository")
public class WorkPersistenceConfig {
}
