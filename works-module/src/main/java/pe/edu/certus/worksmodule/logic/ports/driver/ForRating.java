package pe.edu.certus.worksmodule.logic.ports.driver;

import pe.edu.certus.worksmodule.logic.model.RatingModel;

import java.util.List;

public interface ForRating {
    RatingModel createRating(RatingModel ratingModel);
    void createRatings(List<RatingModel> ratingModels);
    List<RatingModel> findAllRatings();
}