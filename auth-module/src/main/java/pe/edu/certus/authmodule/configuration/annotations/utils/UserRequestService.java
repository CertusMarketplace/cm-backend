package pe.edu.certus.authmodule.configuration.annotations.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;
import pe.edu.certus.authmodule.repository.ports.driver.ForQueryingAuth;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;

@Service
public class UserRequestService {

    private final ForQueryingAuth forQueryingAuth;
    private final ForPeople<PeopleModel, Long> forPeople;
    private final PasswordEncoder passwordEncoder;

    public UserRequestService(ForQueryingAuth forQueryingAuth, ForPeople<PeopleModel, Long> forPeople, PasswordEncoder passwordEncoder) {
        this.forQueryingAuth = forQueryingAuth;
        this.forPeople = forPeople;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void processNewSellerRequest( RequestSellerDto dto) {
        if (forQueryingAuth.findByUserEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado. Por favor, inicie sesión y solicite el rol desde su perfil.");
        }

        // 1. Crear AuthEntity con rol de Vendedor (ID 2)
        AuthEntity user = new AuthEntity();
        user.setUserEmail(dto.getEmail());
        user.setUserPassword(passwordEncoder.encode(dto.getPassword()));
        user.setIdRole(2L); // Rol de Vendedor
        user = forQueryingAuth.save(user);

        // 2. Crear PeopleEntity con todos los datos
        createOrUpdatePeopleProfile(user.getId(), dto);
    }

    @Transactional
    public void processExistingUserSellerRequest(Long userId, RequestSellerDto dto) {
        // 1. Actualizar el rol en AuthEntity
        AuthEntity user = forQueryingAuth.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        user.setIdRole(2L); // Actualizar a rol de Vendedor
        forQueryingAuth.save(user);

        // 2. Actualizar los datos en PeopleEntity
        createOrUpdatePeopleProfile(userId, dto);
    }

    private void createOrUpdatePeopleProfile(Long userId, RequestSellerDto dto) {
        PeopleModel personProfile = PeopleModel.builder()
                .idUser(userId)
                .personName(dto.getName())
                .personLastname(dto.getLastname())
                .personDni(dto.getDni())
                .personMobilePhone(dto.getMobilePhone())
                .personGender(dto.getGender())
                .personInstituteCampus(dto.getInstituteCampus())
                .personInstitutionalEmail(dto.getEmail())
                .personInstitutionalCareer(dto.getInstitutionalCareer())
                .personCurrentTerm(dto.getInstitutionalCycle())
                .build();

        // El método updatePeople de ForPeople debería manejar tanto la creación (si no existe) como la actualización.
        // Si no lo hace, necesitarías un forPeople.createOrUpdate(personProfile);
        forPeople.updatePeople(personProfile);
    }
}