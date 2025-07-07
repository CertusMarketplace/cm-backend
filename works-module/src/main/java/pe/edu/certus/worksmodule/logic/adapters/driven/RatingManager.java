package pe.edu.certus.worksmodule.logic.adapters.driven;

import org.springframework.stereotype.Service;
import pe.edu.certus.worksmodule.logic.model.RatingModel;
import pe.edu.certus.worksmodule.logic.ports.driven.ForManagingRating;
import pe.edu.certus.worksmodule.logic.ports.driver.ForRating;

import java.util.List;

@Service
public class RatingManager implements ForRating {

    private final ForManagingRating forManagingRating;

    public RatingManager(ForManagingRating forManagingRating) {
        this.forManagingRating = forManagingRating;
    }

    @Override
    public RatingModel createRating(RatingModel ratingModel) {
        return forManagingRating.saveRating(ratingModel);
    }

    @Override
    public List<RatingModel> findAllRatings() {
        return forManagingRating.satisfyFindAllRatings();
    }
}