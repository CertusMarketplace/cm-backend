package pe.edu.certus.worksmodule.repository.ports.mapper;

import pe.edu.certus.worksmodule.logic.model.RatingModel;
import pe.edu.certus.worksmodule.repository.entity.RatingEntity;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;

public interface ForBridgingRating {
    RatingEntity toPersistence(RatingModel ratingModel, WorkEntity workEntity);
    RatingModel fromPersistence(RatingEntity ratingEntity);
}