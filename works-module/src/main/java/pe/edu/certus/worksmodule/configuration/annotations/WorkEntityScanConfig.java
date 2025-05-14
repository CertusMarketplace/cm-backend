package pe.edu.certus.worksmodule.configuration.annotations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "pe.edu.certus.worksmodule.repository.entity")
public class WorkEntityScanConfig {
}
