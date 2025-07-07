package pe.edu.certus.worksmodule.logic.ports.mapper;

import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.RatingWebModel;
import pe.edu.certus.worksmodule.logic.model.RatingModel;

public interface ForMappingRating {
    RatingModel fromWeb(RatingWebModel ratingWebModel);
    RatingWebModel toWeb(RatingModel ratingModel);
}