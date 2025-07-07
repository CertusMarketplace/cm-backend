package pe.edu.certus.worksmodule.logic.adapters.mapper;

import pe.edu.certus.worksmodule.logic.adapters.driver.inputs.RatingWebModel;
import pe.edu.certus.worksmodule.logic.model.RatingModel;
import pe.edu.certus.worksmodule.logic.ports.mapper.ForMappingRating;

public class RatingMapper implements ForMappingRating {

    @Override
    public RatingModel fromWeb(RatingWebModel webModel) {
        return RatingModel.builder()
                .ratingId(webModel.ratingId())
                .workId(webModel.workId())
                .userId(webModel.userId())
                .orderId(webModel.orderId())
                .ratingScore(webModel.ratingScore())
                .ratingComment(webModel.ratingComment())
                .build();
    }

    @Override
    public RatingWebModel toWeb(RatingModel model) {
        return new RatingWebModel(
                model.getRatingId(),
                model.getWorkId(),
                model.getUserId(),
                model.getOrderId(),
                model.getRatingScore(),
                model.getRatingComment(),
                model.getRatingCreatedAt()
        );
    }
}