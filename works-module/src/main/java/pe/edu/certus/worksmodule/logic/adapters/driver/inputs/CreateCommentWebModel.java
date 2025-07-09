package pe.edu.certus.worksmodule.logic.adapters.driver.inputs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentWebModel(
        @NotNull(message = "El ID del trabajo es obligatorio")
        Long workId,

        Long parentCommentId,

        @NotBlank(message = "El cuerpo del comentario no puede estar vacío")
        String commentBody
) {}
