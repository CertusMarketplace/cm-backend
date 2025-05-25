package pe.edu.certus.ratingsmodule.repository.adapters.mapper;

import pe.edu.certus.ratingsmodule.logic.model.RatingModel;
import pe.edu.certus.ratingsmodule.repository.entity.RatingEntity;
import pe.edu.certus.ratingsmodule.repository.ports.mapper.ForBridgingRating;

public class RatingPersistenceMapper implements ForBridgingRating {
    @Override
    public RatingEntity toPersistence(RatingModel ratingModel) {
        return RatingEntity.builder()
                .ratingId(ratingModel.getRatingId())
                .workId(ratingModel.getWorkId())
                .userId(ratingModel.getUserId())
                .orderId(ratingModel.getOrderId())
                .ratingScore(ratingModel.getRatingScore())
                .ratingComment(ratingModel.getRatingComment())
                .ratingCreatedAt(ratingModel.getRatingCreatedAt())
                .build();
    }

    @Override
    public RatingModel fromPersistence(RatingEntity ratingEntity) {
        return RatingModel.builder()
                .ratingId(ratingEntity.getRatingId())
                .workId(ratingEntity.getWorkId())
                .userId(ratingEntity.getUserId())
                .orderId(ratingEntity.getOrderId())
                .ratingScore(ratingEntity.getRatingScore())
                .ratingComment(ratingEntity.getRatingComment())
                .ratingCreatedAt(ratingEntity.getRatingCreatedAt())
                .build();
    }
}
