package pe.edu.certus.ratingsmodule.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RatingModel {
    private Long ratingId;
    private Long workId;
    private Long userId;
    private Long orderId;
    private byte ratingScore;
    private String ratingComment;
    private LocalDateTime ratingCreatedAt;
}
