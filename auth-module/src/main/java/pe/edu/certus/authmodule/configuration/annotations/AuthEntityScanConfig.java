package pe.edu.certus.authmodule.configuration.annotations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "pe.edu.certus.authmodule.repository.entity")
public class AuthEntityScanConfig {}