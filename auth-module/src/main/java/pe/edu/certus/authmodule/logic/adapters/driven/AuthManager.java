package pe.edu.certus.authmodule.logic.adapters.driven;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.certus.authmodule.jwt.adapters.JwtManager;
import pe.edu.certus.authmodule.logic.adapters.driver.ResponseWebModel;
import pe.edu.certus.authmodule.logic.model.LoginModel;
import pe.edu.certus.authmodule.logic.ports.driver.ForAuth;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;
import pe.edu.certus.authmodule.repository.ports.driver.ForQueryingAuth;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthManager implements ForAuth {

    private final ForQueryingAuth forQueryingAuth;
    private final JwtManager jwtManager;
    private final PasswordEncoder passwordEncoder;

    public AuthManager(ForQueryingAuth forQueryingAuth, JwtManager jwtManager, PasswordEncoder passwordEncoder) {
        this.forQueryingAuth = forQueryingAuth;
        this.jwtManager = jwtManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public HashMap<String, String> login(LoginModel loginRequest) throws Exception {
        HashMap<String, String> response = new HashMap<>();
        Optional<AuthEntity> userOptional = forQueryingAuth.findByUserEmail(loginRequest.getUserEmail());

        if (userOptional.isEmpty()) {
            response.put("error", "User not registered!");
            return response;
        }

        AuthEntity user = userOptional.get();
        if (passwordEncoder.matches(loginRequest.getUserPassword(), user.getUserPassword())) {
            response.put("jwt", jwtManager.generateJWT(user.getId()));
        } else {
            response.put("error", "Authentication failed");
        }
        return response;
    }

    @Override
    public ResponseWebModel register(AuthEntity user) throws Exception {
        ResponseWebModel response = new ResponseWebModel();
        Optional<AuthEntity> existingUser = forQueryingAuth.findByUserEmail(user.getUserEmail());
        if (existingUser.isPresent()) {
            response.setNumOfErrors(1);
            response.setMessage("User with this email already exists!");
            return response;
        }

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        forQueryingAuth.save(user);
        response.setMessage("User created successfully!");
        return response;
    }
}