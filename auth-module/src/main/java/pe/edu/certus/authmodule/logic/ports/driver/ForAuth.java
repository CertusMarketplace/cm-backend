package pe.edu.certus.authmodule.logic.ports.driver;

<<<<<<< Updated upstream
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
=======
import pe.edu.certus.authmodule.logic.adapters.driver.ResponseWebModel;
import pe.edu.certus.authmodule.logic.model.LoginModel;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;
import java.util.HashMap;

public interface ForAuth {
    HashMap<String, String> login(LoginModel loginRequest) throws Exception;
    ResponseWebModel register( AuthEntity user, String personName, String personLastname) throws Exception;
>>>>>>> Stashed changes
}