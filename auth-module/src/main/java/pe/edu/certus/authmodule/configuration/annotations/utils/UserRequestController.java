package pe.edu.certus.authmodule.configuration.annotations.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserRequestController {

    private final UserRequestService userRequestService;

    public UserRequestController(UserRequestService userRequestService) {
        this.userRequestService = userRequestService;
    }

    @PostMapping("/request-seller-role")
    public ResponseEntity<?> requestSellerRole(@RequestBody RequestSellerDto requestDto) {
        try {
            userRequestService.processNewSellerRequest(requestDto);
            return ResponseEntity.ok(Map.of("message", "Solicitud de vendedor procesada exitosamente. Por favor, inicie sesión."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Ocurrió un error inesperado."));
        }
    }

    @PutMapping("/request-seller-role")
    public ResponseEntity<?> upgradeToSellerRole(Authentication authentication, @RequestBody RequestSellerDto requestDto) {
        try {
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(401).body(Map.of("error", "No autenticado."));
            }
            Long userId = Long.parseLong(authentication.getName());
            userRequestService.processExistingUserSellerRequest(userId, requestDto);
            return ResponseEntity.ok(Map.of("message", "Rol actualizado a vendedor. Por favor, vuelva a iniciar sesión para ver los cambios."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Ocurrió un error inesperado."));
        }
    }
}