package pe.edu.certus.worksmodule.logic.ports.driven;

import pe.edu.certus.worksmodule.logic.model.RatingModel;

import java.util.List;

public interface ForManagingRating {
    RatingModel saveRating(RatingModel ratingModel);
    List<RatingModel> satisfyFindAllRatings();
}