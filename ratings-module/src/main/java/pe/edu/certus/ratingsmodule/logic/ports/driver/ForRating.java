package pe.edu.certus.ratingsmodule.logic.ports.driver;

import java.util.List;

public interface ForRating <RatingModel, Long> {
    void createRating(RatingModel ratingModel);
    RatingModel findRatingById(Long id);
    List<RatingModel> findAllRatings();
    RatingModel updateRating(RatingModel ratingModel);
    void deleteRating(Long id);
}
