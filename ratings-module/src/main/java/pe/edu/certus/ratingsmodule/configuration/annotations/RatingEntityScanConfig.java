package pe.edu.certus.ratingsmodule.configuration.annotations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "pe.edu.certus.ratingsmodule.repository.entity")
public class RatingEntityScanConfig {
}
