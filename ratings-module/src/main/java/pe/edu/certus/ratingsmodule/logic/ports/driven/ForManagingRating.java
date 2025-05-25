package pe.edu.certus.ratingsmodule.logic.ports.driven;

import pe.edu.certus.ratingsmodule.logic.model.RatingModel;

import java.util.List;

public interface ForManagingRating {
    void satisfyCreateRating(RatingModel ratingModel);
    RatingModel satisfyFindRatingById(Long id);
    List<RatingModel> satisfyFindAllRating();
    RatingModel satisfyUpdateRating(RatingModel ratingModel);
    void satisfyDeleteRatingById(Long id);
}
