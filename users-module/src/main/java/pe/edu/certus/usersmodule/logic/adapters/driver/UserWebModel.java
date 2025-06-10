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
        @Pattern(regexp = "^[0-9]{8}@certus\\.edu\\.pe$",

                message = "El correo electrónico debe ser el DNI de 8 dígitos seguido de @certus.edu.pe")
        String userEmail,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 8, max = 10, message = "La contraseña debe tener entre 8 y 10 caracteres")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
                message = "La contraseña debe contener al menos un número, una letra minúscula, una mayúscula y un carácter especial")
        String userPassword,

        LocalDateTime userCreatedAt,
        LocalDateTime userUpdatedAt,
        @NotNull(message = "El estado del usuario no puede ser nulo")

        Boolean userStatus
) {

    public UserWebModel {
        userCreatedAt = userCreatedAt != null ? userCreatedAt : LocalDateTime.now();
        userUpdatedAt = userUpdatedAt != null ? userUpdatedAt : LocalDateTime.now();

    }
}
