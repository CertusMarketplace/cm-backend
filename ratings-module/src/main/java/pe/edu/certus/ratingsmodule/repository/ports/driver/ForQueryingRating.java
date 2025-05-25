package pe.edu.certus.ratingsmodule.repository.ports.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.certus.ratingsmodule.repository.entity.RatingEntity;

import java.util.List;

@Repository
public interface ForQueryingRating extends JpaRepository<RatingEntity, Long> {
}
