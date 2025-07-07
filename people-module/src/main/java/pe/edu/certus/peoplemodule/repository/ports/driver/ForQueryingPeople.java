
package pe.edu.certus.peoplemodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.certus.peoplemodule.repository.entity.PeopleEntity;

import java.util.Optional;

public interface ForQueryingPeople extends JpaRepository<PeopleEntity, Long> {
    Optional<PeopleEntity> findByIdUser(Long idUser);
}