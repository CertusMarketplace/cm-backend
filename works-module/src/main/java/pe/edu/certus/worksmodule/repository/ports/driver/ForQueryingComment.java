package pe.edu.certus.worksmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.certus.worksmodule.repository.entity.CommentEntity;

@Repository
public interface ForQueryingComment extends JpaRepository<CommentEntity, Long> {
}