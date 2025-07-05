package pe.edu.certus.paypalmodule.configuration.annotations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pe.edu.certus.paypalmodule.repository")
public class PaypalPersistenceConfig {
}
