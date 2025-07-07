package pe.edu.certus.worksmodule.logic.ports.driver;

import pe.edu.certus.worksmodule.logic.model.RatingModel;

import java.util.List;

public interface ForRating {
    RatingModel createRating(RatingModel ratingModel);
    List<RatingModel> findAllRatings();
}