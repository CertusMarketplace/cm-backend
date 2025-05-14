package pe.edu.certus.worksmodule.logic.adapters.driver;

import lombok.Builder;
import pe.edu.certus.worksmodule.logic.model.WorkModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record WorkWebModel(
        Integer workId,
        Integer idSellerUser,
        Integer idWorkCategory,
        String workTitle,
        String workDescription,
        String workCategory,
        BigDecimal workPrice,
        Boolean workIsDeleted,
        String workImageUrl,
        LocalDateTime workPublishedAt,
        LocalDateTime workUpdatedAt,
        WorkModel.WorkStatus workStatus) {
}
