
package pe.edu.certus.peoplemodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.certus.peoplemodule.repository.entity.PeopleEntity;

import java.util.Optional;

public interface ForQueryingPeople extends JpaRepository<PeopleEntity, Long> {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes
    Optional<PeopleEntity> findByIdUser(Long idUser);
}