package pe.edu.certus.authmodule.logic.ports.driver;

import com.nimbusds.jose.JOSEException;
import pe.edu.certus.authmodule.logic.model.LoginRequest;
import pe.edu.certus.authmodule.logic.model.RegisterRequest;
import pe.edu.certus.authmodule.logic.model.GoogleLoginModel;

import java.io.IOException;
import java.util.HashMap;

public interface ForAuth {
    String login(LoginRequest request) throws IOException, JOSEException;
    void register(RegisterRequest request);
    HashMap<String, String> loginWithGoogle(GoogleLoginModel request);
}