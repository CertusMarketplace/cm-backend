package pe.edu.certus.worksmodule.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class WorkModel {
    private Long workId;
    private Long idSellerUser;
    private String sellerName;
    private Long idWorkCategory;
    private String workTitle;
    private String workDescription;
    private String workCategory;
    private BigDecimal workPrice;
    @Builder.Default
    private Boolean workIsDeleted = false;
    private String workImageUrl;
    private List<String> imageUrls;
    private String workFilePath;
    private LocalDateTime workPublishedAt;
    private LocalDateTime workUpdatedAt;
    private WorkStatus workStatus;
<<<<<<< Updated upstream
    private Double averageRating;
    private List<RatingModel> ratings;
    private List<CommentModel> comments;
=======
    private double averageRating;
>>>>>>> Stashed changes

    public enum WorkStatus {
        PUBLICADO,
        EN_REVISION,
        RECHAZADO
    }
}