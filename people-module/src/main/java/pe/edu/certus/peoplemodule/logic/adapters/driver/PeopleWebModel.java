package pe.edu.certus.peoplemodule.logic.adapters.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record PeopleWebModel(
        Long personId,
        Long idUser,

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100)
        String personName,

        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(max = 100)
        String personLastname,

        String personProfileImageUrl,

        @Size(max = 8, message = "El DNI debe tener 8 caracteres")
        @Pattern(regexp = "^[0-9]*$", message = "El DNI debe contener solo números")
        String personDni,

        @Size(max = 15)
        String personMobilePhone,

        @Size(max = 1)
        String personGender,

        @Size(max = 100)
        String personInstituteCampus,

        @Size(max = 100)
        String personInstitutionalEmail,

        @Size(max = 100)
        String personInstitutionalCareer,

        Integer personCurrentTerm
) {}