package pe.edu.certus.paypalmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.certus.paypalmodule.repository.entity.WorkPriceEntity;

public interface ForQueryingWorkInPaypal extends JpaRepository< WorkPriceEntity, Long> {
}