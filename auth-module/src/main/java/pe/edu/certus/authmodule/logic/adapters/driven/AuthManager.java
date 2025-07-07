package pe.edu.certus.authmodule.logic.adapters.driven;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.certus.authmodule.jwt.adapters.JwtManager;
import pe.edu.certus.authmodule.logic.model.GoogleLoginModel;
import pe.edu.certus.authmodule.logic.model.LoginRequest;
import pe.edu.certus.authmodule.logic.model.RegisterRequest;
import pe.edu.certus.authmodule.logic.ports.driver.ForAuth;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;
import pe.edu.certus.authmodule.repository.ports.driver.ForQueryingAuth;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthManager implements ForAuth {

    private final ForQueryingAuth forQueryingAuth;
    private final PasswordEncoder passwordEncoder;
    private final JwtManager jwtManager;
    private final ForPeople<PeopleModel, Long> forPeople;
    private final GoogleIdTokenVerifier googleIdTokenVerifier;

    public AuthManager(ForQueryingAuth forQueryingAuth, PasswordEncoder passwordEncoder, JwtManager jwtManager, ForPeople<PeopleModel, Long> forPeople, @Value("${google.client-id}") String clientId) {
        this.forQueryingAuth = forQueryingAuth;
        this.passwordEncoder = passwordEncoder;
        this.jwtManager = jwtManager;
        this.forPeople = forPeople;
        this.googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(clientId))
                .build();
    }


    @Override
    public String login(LoginRequest request) throws IOException, JOSEException {
        // CORRECCIÓN: Usar getEmail() en lugar de getUserEmail()
        AuthEntity user = forQueryingAuth.findByUserEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuario o contraseña incorrectos"));
        // CORRECCIÓN: Usar getPassword() en lugar de getUserPassword()
        if (!passwordEncoder.matches(request.getPassword(), user.getUserPassword())) {
            throw new IllegalArgumentException("Usuario o contraseña incorrectos");
        }
        return jwtManager.generateJWT(user.getId());
    }

    @Override
    public void register(RegisterRequest request) {
        // CORRECCIÓN: Usar getEmail(), getName(), getLastname()
        if (forQueryingAuth.findByUserEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        AuthEntity user = new AuthEntity();
        user.setUserEmail(request.getEmail());
        user.setUserPassword(passwordEncoder.encode(request.getPassword()));
        user.setIdRole(3L); // Por defecto rol de Comprador al registrarse
        user = forQueryingAuth.save(user);

        PeopleModel person = PeopleModel.builder()
                .idUser(user.getId())
                .personName(request.getName())
                .personLastname(request.getLastname())
                .build();
        forPeople.createPeople(person);
    }

    @Override
    public HashMap<String, String> loginWithGoogle(GoogleLoginModel request) {
        try {
            GoogleIdToken idToken = googleIdTokenVerifier.verify(request.getIdToken());
            if (idToken == null) {
                throw new IllegalArgumentException("Invalid Google ID token.");
            }
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            Optional<AuthEntity> userOptional = forQueryingAuth.findByUserEmail(email);
            AuthEntity user;
            if (userOptional.isPresent()) {
                user = userOptional.get();
            } else {
                user = new AuthEntity();
                user.setUserEmail(email);
                user.setUserPassword(passwordEncoder.encode(java.util.UUID.randomUUID().toString()));
                user.setIdRole(3L);
                user = forQueryingAuth.save(user);
                String givenName = (String) payload.get("given_name");
                String familyName = (String) payload.get("family_name");
                String pictureUrl = (String) payload.get("picture");
                PeopleModel newPersonProfile = PeopleModel.builder()
                        .idUser(user.getId())
                        .personName(givenName != null ? givenName : "Usuario")
                        .personLastname(familyName != null ? familyName : "Google")
                        .personProfileImageUrl(pictureUrl)
                        .build();
                forPeople.createPeople(newPersonProfile);
            }
            HashMap<String, String> jwt = new HashMap<>();
            jwt.put("jwt", jwtManager.generateJWT(user.getId()));
            return jwt;
        } catch (Exception e) {
            System.err.println("Error en login con Google: " + e.getMessage());
            e.printStackTrace();
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "Error interno al procesar token de Google.");
            return error;
        }
    }
}