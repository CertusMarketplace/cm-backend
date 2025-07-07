package pe.edu.certus.authmodule.logic.adapters.driven;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.certus.authmodule.jwt.adapters.JwtManager;
import pe.edu.certus.authmodule.logic.ports.driven.ForVerifyingGoogleToken;
import pe.edu.certus.authmodule.logic.ports.driver.ForGoogleAuth;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;
import pe.edu.certus.authmodule.repository.ports.driver.ForQueryingAuth;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;

import java.util.HashMap;
import java.util.Optional;

@Service
public class GoogleAuthManager implements ForGoogleAuth {

    private final ForVerifyingGoogleToken forVerifyingGoogleToken;
    private final ForQueryingAuth forQueryingAuth;
    private final JwtManager jwtManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ForPeople<PeopleModel, Long> forPeople;

    public GoogleAuthManager(ForVerifyingGoogleToken forVerifyingGoogleToken,
                             ForQueryingAuth forQueryingAuth,
                             JwtManager jwtManager,
                             PasswordEncoder passwordEncoder,
                             ForPeople< PeopleModel, Long> forPeople) {
        this.forVerifyingGoogleToken = forVerifyingGoogleToken;
        this.forQueryingAuth = forQueryingAuth;
        this.jwtManager = jwtManager;
        this.passwordEncoder = (BCryptPasswordEncoder) passwordEncoder;
        this.forPeople = forPeople;
    }

    @Override
    public HashMap<String, String> loginWithGoogle(String idTokenString) throws Exception {
        try {
            GoogleIdToken.Payload payload = forVerifyingGoogleToken.verifyGoogleToken(idTokenString);
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
            System.err.println("Error processing Google login: " + e.getMessage());
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "Invalid Google Token or server error.");
            return error;
        }
    }
}