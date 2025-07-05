package pe.edu.certus.authmodule.logic.ports.driver;

import pe.edu.certus.authmodule.logic.adapters.driver.ResponseWebModel;
import pe.edu.certus.authmodule.logic.model.LoginModel;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;

import java.util.HashMap;

public interface ForAuth {
    HashMap<String, String> login(LoginModel loginRequest) throws Exception;
    ResponseWebModel register(AuthEntity user) throws Exception;
}