package pe.edu.certus.ratingsmodule.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;

    @Column(name = "work_id", nullable = false)
    private Long workId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "rating_score", nullable = false)
    private byte ratingScore;

    @Column(name = "rating_comment")
    private String ratingComment;

    @Column(name = "rating_created_at")
    private LocalDateTime ratingCreatedAt;
}