package pe.edu.certus.ratingsmodule.logic.adapters.driver;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RatingWebModel(
        Long ratingId,
        
        Long workId,
        Long userId,
        Long orderId,
        
        @NotNull(message = "La puntuación no puede ser nula")
        @Min(value = 1, message = "La puntuación mínima es 1")
        @Max(value = 5, message = "La puntuación máxima es 5")
        byte ratingScore,
        
        @Size(max = 1000, message = "El comentario no puede exceder los 1000 caracteres")
        String ratingComment,
        
        LocalDateTime ratingCreatedAt
) {
    public RatingWebModel {
        ratingCreatedAt = ratingCreatedAt != null ? ratingCreatedAt : LocalDateTime.now();
    }
}
