package pe.edu.certus.authmodule.logic.adapters.driven;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.certus.authmodule.logic.model.RegisterRequest;
import pe.edu.certus.authmodule.logic.ports.driver.ForRegister;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;
import pe.edu.certus.authmodule.repository.ports.driver.ForQueryingAuth;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;

@Service
public class RegisterManager implements ForRegister {

    private final ForQueryingAuth forQueryingAuth;
    private final PasswordEncoder passwordEncoder;
    private final ForPeople<PeopleModel, Long> forPeople;

    public RegisterManager(ForQueryingAuth forQueryingAuth, PasswordEncoder passwordEncoder, ForPeople<PeopleModel, Long> forPeople) {
        this.forQueryingAuth = forQueryingAuth;
        this.passwordEncoder = passwordEncoder;
        this.forPeople = forPeople;
    }

    @Override
    public void register(RegisterRequest request) {
        if (forQueryingAuth.findByUserEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        AuthEntity user = new AuthEntity();
        user.setUserEmail(request.getEmail());
        user.setUserPassword(passwordEncoder.encode(request.getPassword()));
        user.setIdRole(2L);

        user = forQueryingAuth.save(user);

        PeopleModel person = PeopleModel.builder()
                .idUser(user.getId())
                .personName(request.getName())
                .personLastname(request.getLastname())
                .build();
        forPeople.createPeople(person);
    }
}