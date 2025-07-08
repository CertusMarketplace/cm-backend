package pe.edu.certus.worksmodule.repository.entity;

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

<<<<<<< Updated upstream:works-module/src/main/java/pe/edu/certus/worksmodule/repository/entity/RatingEntity.java
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id", nullable = false)
    private WorkEntity work;
=======
    @Column(name = "work_id", nullable = false)
    private Long workId;
>>>>>>> Stashed changes:ratings-module/src/main/java/pe/edu/certus/ratingsmodule/repository/entity/RatingEntity.java

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
<<<<<<< Updated upstream:works-module/src/main/java/pe/edu/certus/worksmodule/repository/entity/RatingEntity.java

    @PrePersist
    protected void onCreate() {
        ratingCreatedAt = LocalDateTime.now();
    }
=======
>>>>>>> Stashed changes:ratings-module/src/main/java/pe/edu/certus/ratingsmodule/repository/entity/RatingEntity.java
}