package pe.edu.certus.usersmodule.logic.adapters.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserWebModel(
        Long userId,
        Long idRole,

        @NotBlank(message = "El correo electrónico no puede estar vacío")
        @Pattern(regexp = "^.+@certus\\.edu\\.pe$", message = "El correo electrónico debe ser de dominio @certus.edu.pe")
        String userEmail,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String userPassword,

        LocalDateTime userCreatedAt,
        LocalDateTime userUpdatedAt,

        @NotNull(message = "El estado del usuario no puede ser nulo")
        Boolean userStatus
) {

    public UserWebModel {
        userCreatedAt = (userCreatedAt != null) ? userCreatedAt : LocalDateTime.now();
        userUpdatedAt = (userUpdatedAt != null) ? userUpdatedAt : LocalDateTime.now();
    }

    // MÉTODO AÑADIDO PARA LA CORRECCIÓN
    public UserWebModel withUserIdAndRole(Long newUserId, Long newRoleId) {
        return new UserWebModel(
                newUserId,
                newRoleId,
                this.userEmail,
                this.userPassword,
                this.userCreatedAt,
                this.userUpdatedAt,
                this.userStatus
        );
    }
}