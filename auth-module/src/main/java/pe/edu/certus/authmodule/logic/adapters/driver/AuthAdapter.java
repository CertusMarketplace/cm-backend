package pe.edu.certus.authmodule.logic.adapters.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.certus.authmodule.logic.model.GoogleLoginModel;
import pe.edu.certus.authmodule.logic.model.LoginModel;
import pe.edu.certus.authmodule.logic.ports.driver.ForAuth;
import pe.edu.certus.authmodule.logic.ports.driver.ForGoogleAuth;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthAdapter {

    private final ForAuth forAuth;
    private final ForGoogleAuth forGoogleAuth;

    public AuthAdapter(ForAuth forAuth, ForGoogleAuth forGoogleAuth) {
        this.forAuth = forAuth;
        this.forGoogleAuth = forGoogleAuth;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseWebModel> register(@RequestBody AuthEntity user) throws Exception {
        ResponseWebModel response = forAuth.register(user);
        if (response.getNumOfErrors() > 0) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody LoginModel loginRequest) throws Exception {
        HashMap<String, String> login = forAuth.login(loginRequest);
        if (login.containsKey("jwt")) {
            return new ResponseEntity<>(login, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(login, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/google-login")
    public ResponseEntity<HashMap<String, String>> googleLogin(@RequestBody GoogleLoginModel request) throws Exception {
        if (request.getIdToken() == null || request.getIdToken().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HashMap<String, String> response = forGoogleAuth.loginWithGoogle(request.getIdToken());

        if (response.containsKey("jwt")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}