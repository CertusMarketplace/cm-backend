package pe.edu.certus.paypalmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.certus.paypalmodule.repository.entity.PaypalPaymentEntity;

public interface ForQueryingPaypalPayments extends JpaRepository<PaypalPaymentEntity, Long> {}