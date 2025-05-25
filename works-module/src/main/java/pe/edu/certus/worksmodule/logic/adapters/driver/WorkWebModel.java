package pe.edu.certus.worksmodule.logic.adapters.driver;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import pe.edu.certus.worksmodule.logic.model.WorkModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record WorkWebModel(
        Long workId,
        Long idSellerUser,
        Long idWorkCategory,
        
        @NotBlank(message = "El título del trabajo no puede estar vacío")
        @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
        String workTitle,
        
        @NotBlank(message = "La descripción del trabajo no puede estar vacía")
        @Size(min = 20, max = 1000, message = "La descripción debe tener entre 20 y 1000 caracteres")
        String workDescription,
        
        String workCategory,
        
        @NotNull(message = "El precio no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero")
        BigDecimal workPrice,
        
        Boolean workIsDeleted,
        
        String workImageUrl,
        
        LocalDateTime workPublishedAt,
        
        LocalDateTime workUpdatedAt,
        
        WorkModel.WorkStatus workStatus
) {
    public WorkWebModel {
        workIsDeleted = workIsDeleted != null ? workIsDeleted : false;
        workPublishedAt = workPublishedAt != null ? workPublishedAt : LocalDateTime.now();
        workUpdatedAt = workUpdatedAt != null ? workUpdatedAt : LocalDateTime.now();
    }
}
