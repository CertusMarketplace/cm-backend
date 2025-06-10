package pe.edu.certus.usersmodule.configuration.annotations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "pe.edu.certus.usersmodule.repository.entity")
public class UserEntityScanConfig {
}
