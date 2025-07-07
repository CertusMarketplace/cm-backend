package pe.edu.certus.worksmodule.repository.adapters.mapper;

import org.springframework.stereotype.Component;
import pe.edu.certus.worksmodule.logic.model.RatingModel;
import pe.edu.certus.worksmodule.repository.entity.RatingEntity;
import pe.edu.certus.worksmodule.repository.entity.WorkEntity;
import pe.edu.certus.worksmodule.repository.ports.mapper.ForBridgingRating;

@Component
public class RatingPersistenceMapper implements ForBridgingRating {

    @Override
    public RatingEntity toPersistence(RatingModel ratingModel, WorkEntity workEntity) {
        return RatingEntity.builder()
                .ratingId(ratingModel.getRatingId())
                .work(workEntity)
                .userId(ratingModel.getUserId())
                .orderId(ratingModel.getOrderId())
                .ratingScore(ratingModel.getRatingScore())
                .ratingComment(ratingModel.getRatingComment())
                .build();
    }

    @Override
    public RatingModel fromPersistence(RatingEntity ratingEntity) {
        return RatingModel.builder()
                .ratingId(ratingEntity.getRatingId())
                .workId(ratingEntity.getWork().getWorkId())
                .userId(ratingEntity.getUserId())
                .orderId(ratingEntity.getOrderId())
                .ratingScore(ratingEntity.getRatingScore())
                .ratingComment(ratingEntity.getRatingComment())
                .ratingCreatedAt(ratingEntity.getRatingCreatedAt())
                .build();
    }
}