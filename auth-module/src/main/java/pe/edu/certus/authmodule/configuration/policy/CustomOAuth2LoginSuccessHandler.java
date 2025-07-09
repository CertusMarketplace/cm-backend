package pe.edu.certus.authmodule.configuration.policy;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.certus.authmodule.jwt.adapters.JwtManager;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;
import pe.edu.certus.authmodule.repository.ports.driver.ForQueryingAuth;
import pe.edu.certus.peoplemodule.logic.model.PeopleModel;
import pe.edu.certus.peoplemodule.logic.ports.driver.ForPeople;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
public class CustomOAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ForQueryingAuth forQueryingAuth;
    private final ForPeople<PeopleModel, Long> forPeople;
    private final JwtManager jwtManager;
    private final PasswordEncoder passwordEncoder;

    public CustomOAuth2LoginSuccessHandler(ForQueryingAuth forQueryingAuth, ForPeople<PeopleModel, Long> forPeople, JwtManager jwtManager, PasswordEncoder passwordEncoder) {
        this.forQueryingAuth = forQueryingAuth;
        this.forPeople = forPeople;
        this.jwtManager = jwtManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oauth2User.getAttributes();
        String email = (String) attributes.get("email");

        // Paso 1: Encuentra el usuario de autenticación o créalo si no existe.
        AuthEntity user = forQueryingAuth.findByUserEmail(email)
                .orElseGet(() -> createNewUserWithProfile(attributes));

        // Paso 2: Asegúrate de que el perfil tenga la foto de Google si no tenía una.
        updateProfileImageIfNeeded(user.getId(), attributes);

        try {
            // Paso 3: Genera el JWT y redirige al usuario.
            String jwt = jwtManager.generateJWT(user.getId());

            Cookie jwtCookie = new Cookie("jwt_token", jwt);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(60 * 60 * 24); // 1 día
            response.addCookie(jwtCookie);

            response.sendRedirect("/marketplace/auth/oauth-success");

        } catch (Exception e) {
            throw new ServletException("Error al generar el token JWT o al redirigir", e);
        }
    }

    /**
     * Crea un nuevo usuario y su perfil de persona.
     * Se usa solo cuando el usuario no existe en la base de datos.
     */
    private AuthEntity createNewUserWithProfile(Map<String, Object> attributes) {
        AuthEntity newUser = new AuthEntity();
        newUser.setUserEmail((String) attributes.get("email"));
        newUser.setUserPassword(passwordEncoder.encode(UUID.randomUUID().toString())); // Contraseña aleatoria
        newUser.setIdRole(3L); // Rol de comprador por defecto
        AuthEntity savedUser = forQueryingAuth.save(newUser);

        PeopleModel person = PeopleModel.builder()
                .idUser(savedUser.getId())
                .personName((String) attributes.get("given_name"))
                .personLastname((String) attributes.get("family_name"))
                .personProfileImageUrl((String) attributes.get("picture"))
                .personInstitutionalEmail((String) attributes.get("email"))
                .build();
        forPeople.createPeople(person);

        return savedUser;
    }

    /**
     * Revisa el perfil de un usuario existente y actualiza la foto de perfil
     * si no tiene una y Google sí la proporciona.
     */
    private void updateProfileImageIfNeeded(Long userId, Map<String, Object> attributes) {
        try {
            PeopleModel personProfile = forPeople.findPeopleById(userId);

            boolean needsUpdate = personProfile.getPersonProfileImageUrl() == null || personProfile.getPersonProfileImageUrl().isEmpty();
            String googlePictureUrl = (String) attributes.get("picture");

            if (needsUpdate && googlePictureUrl != null && !googlePictureUrl.isEmpty()) {
                personProfile.setPersonProfileImageUrl(googlePictureUrl);
                forPeople.updatePeople(personProfile);
            }
        } catch (EntityNotFoundException e) {
            // Esto no debería pasar si el usuario fue creado correctamente, pero es una buena práctica manejarlo.
            System.err.println("Error: No se encontró el perfil de persona para el usuario con ID: " + userId);
        } catch (Exception e) {
            System.err.println("Error al intentar actualizar la imagen de perfil para el usuario con ID: " + userId + ". " + e.getMessage());
        }
    }
}