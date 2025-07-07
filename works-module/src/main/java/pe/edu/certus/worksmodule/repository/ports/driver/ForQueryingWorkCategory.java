package pe.edu.certus.worksmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.certus.worksmodule.repository.entity.WorkCategoryEntity;

@Repository
public interface ForQueryingWorkCategory extends JpaRepository<WorkCategoryEntity, Long> {
}