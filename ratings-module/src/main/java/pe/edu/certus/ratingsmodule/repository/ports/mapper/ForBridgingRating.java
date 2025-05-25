package pe.edu.certus.ratingsmodule.repository.ports.mapper;

import org.springframework.stereotype.Service;
import pe.edu.certus.ratingsmodule.logic.model.RatingModel;
import pe.edu.certus.ratingsmodule.repository.entity.RatingEntity;

@Service
public interface ForBridgingRating {

    RatingEntity toPersistence(RatingModel ratingModel);

    RatingModel fromPersistence(RatingEntity ratingEntity);

}
