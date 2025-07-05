package pe.edu.certus.paypalmodule.configuration.annotations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "pe.edu.certus.paypalmodule.repository.entity")
public class PaypalEntityScanConfig {
}
