package pe.edu.certus.authmodule.logic.adapters.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.certus.authmodule.logic.model.GoogleLoginModel;
import pe.edu.certus.authmodule.logic.model.LoginRequest;
import pe.edu.certus.authmodule.logic.model.RegisterRequest;
import pe.edu.certus.authmodule.logic.ports.driver.ForAuth;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthAdapter {
    private final ForAuth forAuth;

    public AuthAdapter(ForAuth forAuth) {
        this.forAuth = forAuth;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String jwt = forAuth.login(request);
            HashMap<String, String> response = new HashMap<>();
            response.put("jwt", jwt);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(401).body(error);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            forAuth.register(request);
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Usuario registrado correctamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/google-login")
    public ResponseEntity<?> loginWithGoogle(@RequestBody GoogleLoginModel request) {
        try {
            return ResponseEntity.ok(forAuth.loginWithGoogle(request));
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(401).body(error);
        }
    }
}