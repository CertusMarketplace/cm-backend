package pe.edu.certus.peoplemodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.certus.peoplemodule.repository.entity.PeopleEntity;

public interface ForQueryingPeople extends JpaRepository<PeopleEntity, Long> {
}