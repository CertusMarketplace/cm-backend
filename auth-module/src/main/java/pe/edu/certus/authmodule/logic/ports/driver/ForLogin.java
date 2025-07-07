package pe.edu.certus.authmodule.logic.ports.driver;

import pe.edu.certus.authmodule.logic.model.LoginRequest;

public interface ForLogin {
    String login( LoginRequest request);
}
