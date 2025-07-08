package pe.edu.certus.authmodule.logic.adapters.driven;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.certus.authmodule.jwt.adapters.JwtManager;
import pe.edu.certus.authmodule.logic.ports.driven.ForVerifyingGoogleToken;
import pe.edu.certus.authmodule.logic.ports.driver.ForGoogleAuth;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;
import pe.edu.certus.authmodule.repository.ports.driver.ForQueryingAuth;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;
<<<<<<< Updated upstream
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class GoogleAuthManager implements ForGoogleAuth {

    private final ForVerifyingGoogleToken forVerifyingGoogleToken;
    private final ForQueryingAuth forQueryingAuth;
    private final ForPeople forPeople;
    private final JwtManager jwtManager;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    private final BCryptPasswordEncoder passwordEncoder;
    private final ForPeople<PeopleModel, Long> forPeople;
=======
    private final PasswordEncoder passwordEncoder;
>>>>>>> Stashed changes
=======
    private final PasswordEncoder passwordEncoder;
>>>>>>> Stashed changes

    public GoogleAuthManager(ForVerifyingGoogleToken forVerifyingGoogleToken,
                             ForQueryingAuth forQueryingAuth,
                             ForPeople forPeople,
                             JwtManager jwtManager,
                             PasswordEncoder passwordEncoder,
                             ForPeople< PeopleModel, Long> forPeople) {
        this.forVerifyingGoogleToken = forVerifyingGoogleToken;
        this.forQueryingAuth = forQueryingAuth;
        this.forPeople = forPeople;
        this.jwtManager = jwtManager;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        this.passwordEncoder = (BCryptPasswordEncoder) passwordEncoder;
        this.forPeople = forPeople;
=======
        this.passwordEncoder = passwordEncoder;
>>>>>>> Stashed changes
=======
        this.passwordEncoder = passwordEncoder;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
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
=======
=======
>>>>>>> Stashed changes
                boolean isCertusEmail = email != null && email.toLowerCase().endsWith("@certus.edu.pe");
                AuthEntity newUser = new AuthEntity();
                newUser.setUserEmail(email);
                newUser.setUserPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                newUser.setIdRole(isCertusEmail ? 2L : 3L);
                user = forQueryingAuth.save(newUser);

                String fullName = (String) payload.get("name");
                String firstName = fullName;
                String lastName = "";
                if (fullName != null && fullName.contains(" ")) {
                    firstName = fullName.substring(0, fullName.lastIndexOf(" "));
                    lastName = fullName.substring(fullName.lastIndexOf(" ") + 1);
                }

                PeopleModel newPerson = PeopleModel.builder()
                        .idUser(user.getId())
                        .personName(firstName)
                        .personLastname(lastName)
                        .personInstitutionalEmail(isCertusEmail ? email : null)
                        .build();
                forPeople.createPeople(newPerson);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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