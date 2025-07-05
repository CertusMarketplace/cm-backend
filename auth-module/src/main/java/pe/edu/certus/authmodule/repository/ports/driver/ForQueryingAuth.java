package pe.edu.certus.authmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;

import java.util.Optional;

public interface ForQueryingAuth extends JpaRepository<AuthEntity, Long> {
    Optional<AuthEntity> findByUserEmail(String userEmail);
}