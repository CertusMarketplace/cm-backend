package pe.edu.certus.worksmodule.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set; // Importa Set

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
    private Double averageRating;
    private Set<RatingModel> ratings;
    private Set<CommentModel> comments;

    public enum WorkStatus {
        PUBLICADO,
        EN_REVISION,
        RECHAZADO
    }
}