package pe.edu.certus.ratingsmodule.logic.ports.mapper;

import pe.edu.certus.ratingsmodule.logic.adapters.driver.RatingWebModel;
import pe.edu.certus.ratingsmodule.logic.model.RatingModel;

public interface ForMappingRating {

    RatingModel fromWeb( RatingWebModel ratingWebModel );

    RatingWebModel toWeb( RatingModel ratingModel );

}
