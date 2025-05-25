package pe.edu.certus.worksmodule.logic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class WorkModel {

    private Long workId;
    private Long idSellerUser;
    private Long idWorkCategory;
    private String workTitle;
    private String workDescription;
    private String workCategory;
    private BigDecimal workPrice;
    private Boolean workIsDeleted;
    private String workImageUrl;
    private LocalDateTime workPublishedAt;
    private LocalDateTime workUpdatedAt;
    private WorkStatus workStatus;

    public enum WorkStatus {
        PUBLICADO,
        EN_REVISION,
        RECHAZADO
    };
}
