package pe.edu.certus.authmodule.logic.ports.driver;

import pe.edu.certus.authmodule.logic.model.RegisterRequest;

public interface ForRegister {
    void register( RegisterRequest request);
}