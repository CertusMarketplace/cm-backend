package pe.edu.certus.peoplemodule.logic.adapters.driver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record PeopleWebModel(
        Long personId,
        
        Long idUser,
        Long idCareer,
        
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
        String personName,
        
        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(max = 100, message = "El apellido no puede exceder los 100 caracteres")
        String personLastname,
        
        @Size(max = 8, message = "El DNI debe tener 8 caracteres")
        @Pattern(regexp = "^[0-9]*$", message = "El DNI debe contener solo números")
        String personDni,
        
        @Size(max = 15, message = "El número de teléfono no puede exceder los 15 caracteres")
        String personMobilePhone,
        
        @Size(max = 1, message = "El género debe ser un solo carácter")
        String personGender,
        
        @Size(max = 100, message = "La ubicación del instituto no puede exceder los 100 caracteres")
        String personInstituteLocation,
        
        @Size(max = 100, message = "El correo institucional no puede exceder los 100 caracteres")
        String personInstitutionalEmail,
        
        Integer personInstitutionalCycle
) {
}