package pe.edu.certus.authmodule.configuration.annotations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pe.edu.certus.authmodule.repository")
public class AuthPersistenceConfig {}