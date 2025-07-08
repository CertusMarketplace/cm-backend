package pe.edu.certus.worksmodule.logic.adapters.driver.inputs;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import pe.edu.certus.worksmodule.logic.model.WorkModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record WorkWebModel(
        Long workId,
        Long idSellerUser,
        @NotNull(message = "La categoría es obligatoria")
        Long idWorkCategory,

        @NotBlank(message = "El título del trabajo no puede estar vacío")
        @Size(min = 5, max = 100)
        String workTitle,

        @NotBlank(message = "La descripción del trabajo no puede estar vacía")
        @Size(min = 20, max = 1000)
        String workDescription,

<<<<<<< Updated upstream:works-module/src/main/java/pe/edu/certus/worksmodule/logic/adapters/driver/inputs/WorkWebModel.java
=======
        String workCategory,

>>>>>>> Stashed changes:works-module/src/main/java/pe/edu/certus/worksmodule/logic/adapters/driver/WorkWebModel.java
        @NotNull(message = "El precio no puede ser nulo")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
        BigDecimal workPrice,

<<<<<<< Updated upstream:works-module/src/main/java/pe/edu/certus/worksmodule/logic/adapters/driver/inputs/WorkWebModel.java
        String workImageUrl,
        List<String> imageUrls,
        WorkModel.WorkStatus workStatus,
        Boolean workIsDeleted,
        LocalDateTime workPublishedAt,
        LocalDateTime workUpdatedAt,

        String workCategory,
        String sellerName,
        Double averageRating,
        List< RatingWebModel > ratings,
        List< CommentWebModel > comments
) {}
=======
        Boolean workIsDeleted,

        String workImageUrl,

        LocalDateTime workPublishedAt,

        LocalDateTime workUpdatedAt,

        WorkModel.WorkStatus workStatus,

        double averageRating
) {
    public WorkWebModel {
        workIsDeleted = workIsDeleted != null ? workIsDeleted : false;
        workPublishedAt = workPublishedAt != null ? workPublishedAt : LocalDateTime.now();
        workUpdatedAt = workUpdatedAt != null ? workUpdatedAt : LocalDateTime.now();
    }
}
>>>>>>> Stashed changes:works-module/src/main/java/pe/edu/certus/worksmodule/logic/adapters/driver/WorkWebModel.java
