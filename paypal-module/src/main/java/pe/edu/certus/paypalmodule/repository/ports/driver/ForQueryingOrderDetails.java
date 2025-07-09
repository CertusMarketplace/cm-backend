package pe.edu.certus.paypalmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.certus.paypalmodule.repository.entity.OrderDetailEntity;

@Repository
public interface ForQueryingOrderDetails extends JpaRepository<OrderDetailEntity, Long> {
}