package pe.edu.certus.worksmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.certus.worksmodule.repository.entity.WorkImageEntity;

@Repository
public interface ForQueryingWorkImage extends JpaRepository<WorkImageEntity, Long> {
}