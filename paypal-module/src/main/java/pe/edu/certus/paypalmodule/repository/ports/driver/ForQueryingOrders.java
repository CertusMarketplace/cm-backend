package pe.edu.certus.paypalmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.certus.paypalmodule.repository.entity.OrderEntity;

public interface ForQueryingOrders extends JpaRepository< OrderEntity, Long> {
}
