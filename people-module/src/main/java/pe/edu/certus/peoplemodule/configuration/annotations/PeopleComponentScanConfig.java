package pe.edu.certus.peoplemodule.configuration.annotations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "pe.edu.certus.peoplemodule")
public class PeopleComponentScanConfig {
}