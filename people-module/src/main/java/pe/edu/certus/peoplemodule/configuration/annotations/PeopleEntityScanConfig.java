package pe.edu.certus.peoplemodule.configuration.annotations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "pe.edu.certus.peoplemodule.repository.entity")
public class PeopleEntityScanConfig {
}
