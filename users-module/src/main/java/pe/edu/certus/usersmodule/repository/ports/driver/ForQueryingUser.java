package pe.edu.certus.usersmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.certus.usersmodule.repository.entity.UserEntity;

public interface ForQueryingUser extends JpaRepository< UserEntity, Long> {
}
