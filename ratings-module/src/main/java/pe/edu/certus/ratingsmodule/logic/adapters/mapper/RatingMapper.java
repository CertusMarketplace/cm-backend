package pe.edu.certus.ratingsmodule.logic.adapters.mapper;

import pe.edu.certus.ratingsmodule.logic.adapters.driver.RatingWebModel;
import pe.edu.certus.ratingsmodule.logic.model.RatingModel;
import pe.edu.certus.ratingsmodule.logic.ports.mapper.ForMappingRating;

public class RatingMapper implements ForMappingRating {

    @Override
    public RatingModel fromWeb(RatingWebModel ratingWebModel) {
        return RatingModel.builder()
                .ratingId(ratingWebModel.ratingId())
                .workId(ratingWebModel.workId())
                .userId(ratingWebModel.userId())
                .orderId(ratingWebModel.orderId())
                .ratingScore(ratingWebModel.ratingScore())
                .ratingComment(ratingWebModel.ratingComment())
                .ratingCreatedAt(ratingWebModel.ratingCreatedAt())
                .build();
    }

    @Override
    public RatingWebModel toWeb(RatingModel ratingModel) {
        return new RatingWebModel(
                ratingModel.getRatingId(),
                ratingModel.getWorkId(),
                ratingModel.getUserId(),
                ratingModel.getOrderId(),
                ratingModel.getRatingScore(),
                ratingModel.getRatingComment(),
                ratingModel.getRatingCreatedAt()
        );
    }
}
